package com.xml2023.smestajmicroservice.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.neo4j.driver.Result;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.InternalNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jClient.RecordFetchSpec;
import org.springframework.data.neo4j.core.Neo4jOperations.ExecutableQuery;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Service;

import com.xml2023.mainapp.ActiveResExistsForSmestajRequest;
import com.xml2023.mainapp.ActiveResExistsForSmestajResponse;
import com.xml2023.mainapp.KorisnikGrpcGrpc;
import com.xml2023.mainapp.RezervacijaGrpcGrpc;
import com.xml2023.mainapp.KorisnikGrpcGrpc.KorisnikGrpcBlockingStub;
import com.xml2023.mainapp.NekoOcenioSmestajRequest;
import com.xml2023.mainapp.NekoOcenioSmestajResponse;
import com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaRequest;
import com.xml2023.mainapp.NovaOcenaSmestajaNotifikacijaResponse;
import com.xml2023.mainapp.RezervacijaGrpcGrpc.RezervacijaGrpcBlockingStub;
import com.xml2023.mainapp.neo4j.model.Korisnik;
import com.xml2023.smestajmicroservice.dtos.OcenaSmestajaDTO;
import com.xml2023.smestajmicroservice.dtos.SmestajDTO;
import com.xml2023.smestajmicroservice.dtos.TerminDTO;
import com.xml2023.smestajmicroservice.mappers.SmestajBasicMapper;
import com.xml2023.smestajmicroservice.model.data.OcenaSmestaj;
import com.xml2023.smestajmicroservice.model.data.Pogodnost;
import com.xml2023.smestajmicroservice.model.data.Smestaj;
import com.xml2023.smestajmicroservice.model.data.Termin;
import com.xml2023.smestajmicroservice.repositories.Neo4JKorisnikRep;
//import com.xml2023.smestajmicroservice.repositories.Neo4JOcenaSmestajaRep;
import com.xml2023.smestajmicroservice.repositories.Neo4JSmestajRep;
//import com.xml2023.smestajmicroservice.neo4j.repositories.Neo4JSmestajRep;
import com.xml2023.smestajmicroservice.repositories.OcenaSmestajRep;
import com.xml2023.smestajmicroservice.repositories.PogodnostRepository;
import com.xml2023.smestajmicroservice.repositories.SmestajRep;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


@Service
public class SmestajService {

	@Autowired
	private SmestajBasicMapper smestajMapper;
	@Autowired
	private SmestajRep smestajRep;
	@Autowired 
	private PogodnostRepository pogRep;
	@Autowired OcenaSmestajRep ocenaRep;
	
	@Autowired
	private Neo4JSmestajRep smestajNeoRep;
//	@Autowired 
//	private Neo4JOcenaSmestajaRep ocenaNeoRep;
	@Autowired
	private Neo4JKorisnikRep korisnikNeoRep;
	
	//private String reservationHost= "reservation";
	private String smestajHost = "smestaj";
	private String reglogHost= "reglog";
	
	public SmestajDTO createNew(SmestajDTO s) {
		//u maperu se definisu i cuvaju svi objekti koji su ugnjezdeni u objekat smestaj
		Smestaj tmp = smestajMapper.fromDTO(s);
		this.smestajRep.save(tmp);
		
		
		//DODAVANJE CVORA SMESTAJ U GRAF BAZU; IZDVOJENA SAMO POLJA OD INTERESA;
		com.xml2023.mainapp.neo4j.model.Smestaj tmp2 = new com.xml2023.mainapp.neo4j.model.Smestaj();
		tmp2.setId(tmp.getId());
//		tmp2.setDobijeneOcene(new ArrayList<com.xml2023.mainapp.neo4j.model.OcenaSmestaj>());
//		tmp2.setKlijenti(new ArrayList<Korisnik>());
		tmp2.setVlasnik(s.getVlasnikId());
		this.smestajNeoRep.save(tmp2);
		return s;
	}

	public SmestajDTO editSmestaj(SmestajDTO smestaj, String smestajId, String vlasnikId) {
		// TODO Auto-generated method stub
		Smestaj s = smestajRep.findById(smestajId).orElse(null);
		//Da li smestaj postoji?
		if(s==null) {
			return null;
		}
		//Da li je vlasnikId vlasnik tog smestaja?
		if(!s.getVlasnik().equals(vlasnikId)) {
			return null;
		}
		
		s = smestajMapper.editSmestaj(s, smestaj);
		smestajRep.save(s);
		
		//cisto da proverim maper u dbgeru
		return smestajMapper.toDTO(s);
	}

	public SmestajDTO removeSmestaj(String smestajId, String vlasnikId) {
		// TODO Auto-generated method stub
		Smestaj s = smestajRep.findById(smestajId).orElse(null);
		//da li postoji smestaj sa tim id-om?
		if(s == null) {
			return null;
		}
		//da li smestaj pripada vlasniku sa id-om vlasnikId 
		if(!s.getVlasnik().equals(vlasnikId)) {
			return null;
		}
		//da li ima trenutno aktivnih rezervacija prema tom smestaju
		System.out.println("GRPC KOMUNIKACIJA NA RELACIJI SMESTAJ - RESERVATION SERVICE[provera da li postoje aktivne rezervacije vezane za smestaj]");
		ManagedChannel channel = ManagedChannelBuilder.forAddress("reservation", 7978).usePlaintext().build();
		RezervacijaGrpcBlockingStub rezServBlockStub = RezervacijaGrpcGrpc.newBlockingStub(channel);
		ActiveResExistsForSmestajRequest req = ActiveResExistsForSmestajRequest.newBuilder().setUserId(smestajId).build();
		ActiveResExistsForSmestajResponse response = rezServBlockStub.resExistsForSmestaj(req);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		
		System.out.println("KRAJ GRPC KOMUNIKACIJE NA RELACIJI SMESTAJ - RESERVATION SERVICE[provera da li postoje aktivne rezervacije vezane za smestaj]");
		if(response.getExists()) {
			System.out.println("SMESTAJ IMA AKTIVNU REZERVACIJU;");
			return null;
		}
		smestajRep.delete(s);
		return smestajMapper.toDTO(s);
	}

	public SmestajDTO getByIdSmestaja(String smestajId) {
		// TODO Auto-generated method stub
		Smestaj s = smestajRep.findById(smestajId).orElse(null);
		if(s == null)
			return null;
		return smestajMapper.toDTO(s);
	}

	public Collection<SmestajDTO> getAll() {
		// TODO Auto-generated method stub
		Collection<Smestaj> smestajList = smestajRep.findAll();
		Collection<SmestajDTO> retList = new ArrayList<SmestajDTO>();
		for(Smestaj s: smestajList) {
			retList.add(smestajMapper.toDTO(s));
		}
		return retList;
	}

	public Collection<String> getAllPogodnosti() {
		// TODO Auto-generated method stub
		Pogodnost p = pogRep.findAll().get(0);
		return p.getNazivi().stream().collect(Collectors.toList());
	}

	public OcenaSmestajaDTO giveRatingToSmestaj(String userId, String smestajId, OcenaSmestajaDTO ocena) {
		OcenaSmestaj o= ocenaRep.findByGostAndSmestaj(userId,smestajId).orElse(null); 
		if(o==null) {
			o=new OcenaSmestaj( smestajId, userId, ocena.getOcena(), LocalDateTime.now());
		}else {
			o.setDatum(LocalDateTime.now());
			o.setOcena(ocena.getOcena());
		}
		ocenaRep.save(o);
		
		Korisnik kor = this.korisnikNeoRep.findById(userId).orElse(null);
		if(kor != null) {
			com.xml2023.mainapp.neo4j.model.Smestaj sme = this.smestajNeoRep.findById(smestajId).orElse(null);		
			//ako ocena vec postoji
			boolean vecPostoji = false;
			for(com.xml2023.mainapp.neo4j.model.OcenaSmestaj tt : kor.getDateOcene()) {
				if(tt.getSmestaj().getId().equals(smestajId)) {
					vecPostoji = true;
					tt.setDatumIVreme(LocalDateTime.now());
					tt.setOcena(o.getOcena());
					this.korisnikNeoRep.save(kor);
					break;
				}
			}
			if(vecPostoji == false) {
				com.xml2023.mainapp.neo4j.model.OcenaSmestaj tmpOcena = new com.xml2023.mainapp.neo4j.model.OcenaSmestaj();
				tmpOcena.setDatumIVreme(LocalDateTime.now());
				tmpOcena.setOcena(ocena.getOcena());
				tmpOcena.setSmestaj(sme);
				kor.getDateOcene().add(tmpOcena);
				this.korisnikNeoRep.save(kor);
			}
//			this.korisnikNeoRep.save(kor);
		}
		
		Smestaj s = this.smestajRep.findById(smestajId).orElse(null);
		if(s != null) {
			if(novaOcenaSmestajaNotificationEnabled(s.getVlasnik())) {
				newSmestajOcenaNotify(o);
			}
		}
		
		return new OcenaSmestajaDTO(o.getId(), o.getSmestaj(), o.getGost(), o.getOcena(), o.getDatum());
	}
	
	//provera da li je host id-a idVlasnik u svojim podesavanjima ukljucio obavestenja za novu ocenu smestaja
	public boolean novaOcenaSmestajaNotificationEnabled(String idVlasnika) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(reglogHost, 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub bs = KorisnikGrpcGrpc.newBlockingStub(channel);
		NovaOcenaSmestajaNotifikacijaRequest rqst = NovaOcenaSmestajaNotifikacijaRequest.newBuilder().setIdKorisnika(idVlasnika).build();
		NovaOcenaSmestajaNotifikacijaResponse rspns = bs.novaOcenaSmestajaNotStatus(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		return rspns.getStanje();
	}
	
	public void newSmestajOcenaNotify(OcenaSmestaj os) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(reglogHost, 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub bs = KorisnikGrpcGrpc.newBlockingStub(channel);
		NekoOcenioSmestajRequest rqst = NekoOcenioSmestajRequest.newBuilder().setIdKorisnika(os.getGost()).setIdSmestaja(os.getSmestaj()).setOcena(os.getOcena()).build();
		NekoOcenioSmestajResponse rspns = bs.newRankSmestaj(rqst);
		channel.shutdown();

		boolean terminated = false;
		while (!terminated) {
		  try {
		    // Wait for the channel to terminate gracefully
		    terminated = channel.awaitTermination(10, TimeUnit.SECONDS);
		  } catch (InterruptedException e) {
		    // Handle the exception if necessary
		    e.printStackTrace();
		  }
		}
		if(rspns.getResult()) {
			System.out.println("USPESNO POSLATO OBAVESTENJE O NOVOJ OCENI SMESTAJA");
		}
	}

	@Autowired
	private Neo4jTemplate neo4jTemplate;
	@Autowired
	private Neo4jClient neo4jClient;
	
	public List<SmestajDTO> getRecommended(String userId) {

//		String cypher = String.format("MATCH (k:Korisnik)-[:REZERVISE]->(s:Smestaj)<-[rat:OCENJUJE]-(o:Korisnik)\n" +
//		        "WHERE k.id = '%s' AND o.id <> '%s'\n" +
//		        "WITH s, collect(DISTINCT s.id) AS otherAccommodationIds\n" +
//		        "MATCH (s2:Smestaj)\n" +
//		        "WHERE s2.id IN otherAccommodationIds\n" +
//		        "WITH s, s2, size(otherAccommodationIds) AS numSimilarUsers, avg(toFloat(s2.ocena)) AS avgRating, otherAccommodationIds\n" +
//		        "WHERE numSimilarUsers >= 2 AND avgRating >= 3\n" +
//		        "MATCH (s)<-[:REZERVISE]-(o)-[r:OCENJUJE]->(s2)\n" +
//		        "WHERE s2.id IN otherAccommodationIds AND o.id <> '%s'\n" +
//		        "AND datetime(r.datumIVreme).month >= datetime().month - 3 AND r.ocena >= 3\n" +
//		        "WITH s, count(*) AS numGoodRatings, numSimilarUsers, avgRating\n" +
//		        "WHERE numGoodRatings > 5\n" +
//		        "WITH s, (toFloat(numGoodRatings) / toFloat(numSimilarUsers)) AS similarity, avgRating, numSimilarUsers\n" +
//		        "RETURN s.id AS smestajId, similarity + avgRating AS ukupnaOcena\n" +
//		        "ORDER BY ukupnaOcena DESC\n" +
//		        "LIMIT 10", userId, userId, userId);
		String cypher = String.format("MATCH (k1:Korisnik {id: '%s'})-[:REZERVISE]->(s:Smestaj)<-[:REZERVISE]-(k2:Korisnik) where k2.id <> k1.id MATCH (k2)-[oc1:OCENJUJE]->(s)<-[oc2:OCENJUJE]-(k1) WHERE abs(oc1.ocena - oc2.ocena) <= 1 WITH s, collect(DISTINCT s.id) AS listaSmestaja MATCH(s2:Smestaj)-[o:OCENJUJE]-() WHERE s2.id IN listaSmestaja WITH s2, avg(toFloat(o.ocena)) AS avgOcena WHERE avgOcena >= 3 WITH collect(DISTINCT s2.id) as listaSmestaja2 OPTIONAL MATCH(s3:Smestaj)-[oc3:OCENJUJE]-() WHERE s3.id IN listaSmestaja2 AND oc3.datumIVreme >= datetime().month - 3 AND oc3.ocena < 3 WITH COUNT(oc3) as num_ratings, collect(DISTINCT s3.id) as listaSmestaja3, listaSmestaja2 MATCH(s4:Smestaj) WHERE s4.id IN listaSmestaja2 and NOT s4.id IN listaSmestaja3 WITH collect(DISTINCT s4) as listaSmestajaFinal UNWIND listaSmestajaFinal AS smestaj MATCH (s5:Smestaj {id: smestaj.id}) MATCH(k3:Korisnik)-[oc4:OCENJUJE]->(s5) WHERE oc4.ocena IS NOT NULL WITH s5, avg(toFloat(oc4.ocena)) as avg_rating RETURN s5 AS smestaji ORDER BY avg_rating DESC LIMIT 10", userId);
		Collection<Map<String,Object>> result = neo4jClient.query(cypher).fetch().all();
		
//		List<com.xml2023.mainapp.neo4j.model.Smestaj> goodAccomodation = new ArrayList<com.xml2023.mainapp.neo4j.model.Smestaj>();
//		Map<String, Object> params = new HashMap<>();
//		params.put("userId", userId);
//		goodAccomodation = neo4jTemplate.findAll(cypher, params, com.xml2023.mainapp.neo4j.model.Smestaj.class);
		
		List<SmestajDTO> retList = new ArrayList<SmestajDTO>();
		for(Map<String, Object> tmp : result) {
			for(Map.Entry<String, Object> entry : tmp.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				InternalNode value2 = (InternalNode) value;
//				Collection<InternalNode> tmpList = (Collection<InternalNode>) value;
				
				
				Value idSmestaja = value2.get("id");
				String idSmeTemp = idSmestaja.toString();
				String idSme = idSmeTemp.substring(1, idSmeTemp.length()-1);
				if(idSme != null) {
					Smestaj acc = this.smestajRep.findById(idSme).orElse(null);
					if(acc != null) {
						retList.add(smestajMapper.toDTO(acc));
					}
				}
//				for(InternalNode in : tmpList) {
//					Value idSmestaja = in.get("id");
//					String idSme = idSmestaja.toString();
//					idSme = idSme.substring(1, idSme.length() - 1);
//					if(idSme != null) {
//						Smestaj acc = this.smestajRep.findById(idSme).orElse(null);
//						if(acc != null) {
//							retList.add(smestajMapper.toDTO(acc));
//						}
//						
//					}
//				}
				
			}
		}
		return retList;
	}
}
