package com.xml2023.smestajmicroservice.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7978).usePlaintext().build();
		RezervacijaGrpcBlockingStub rezServBlockStub = RezervacijaGrpcGrpc.newBlockingStub(channel);
		ActiveResExistsForSmestajRequest req = ActiveResExistsForSmestajRequest.newBuilder().setUserId(smestajId).build();
		ActiveResExistsForSmestajResponse response = rezServBlockStub.resExistsForSmestaj(req);
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
	public float getProsecnaOcena(Smestaj s) {
//		Collection<OcenaSmestaj> ocene= s.getListaOcena();
//		float uk=0;
//		for(OcenaSmestaj o : ocene) {
//			uk+=o.getOcena();
//		}
//		return uk/ocene.size();	
		return 0F;
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
		com.xml2023.mainapp.neo4j.model.Smestaj sme = this.smestajNeoRep.findById(smestajId).orElse(null);
		com.xml2023.mainapp.neo4j.model.OcenaSmestaj tmpOcena = new com.xml2023.mainapp.neo4j.model.OcenaSmestaj();
		tmpOcena.setDatumIVreme(LocalDateTime.now());
		tmpOcena.setOcena(ocena.getOcena());
		tmpOcena.setSmestaj(sme);
		kor.getDateOcene().add(tmpOcena);
//		sme.getDobijeneOcene().add(tmpOcena);
		
//		this.ocenaNeoRep.save(tmpOcena);
		this.korisnikNeoRep.save(kor);
//		this.smestajNeoRep.save(sme);

		if(kor != null) {
			kor.getDateOcene().add(tmpOcena);
		}
		
		this.korisnikNeoRep.save(kor);
		
		if(novaOcenaSmestajaNotificationEnabled(userId)) {
			newSmestajOcenaNotify(o);
		}
		return new OcenaSmestajaDTO(o.getId(), o.getSmestaj(), o.getGost(), o.getOcena(), o.getDatum());
	}
	
	//provera da li je host id-a idVlasnik u svojim podesavanjima ukljucio obavestenja za novu ocenu smestaja
	public boolean novaOcenaSmestajaNotificationEnabled(String idVlasnika) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub bs = KorisnikGrpcGrpc.newBlockingStub(channel);
		NovaOcenaSmestajaNotifikacijaRequest rqst = NovaOcenaSmestajaNotifikacijaRequest.newBuilder().setIdKorisnika(idVlasnika).build();
		NovaOcenaSmestajaNotifikacijaResponse rspns = bs.novaOcenaSmestajaNotStatus(rqst);
		return rspns.getStanje();
	}
	
	public void newSmestajOcenaNotify(OcenaSmestaj os) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 7979).usePlaintext().build();
		KorisnikGrpcBlockingStub bs = KorisnikGrpcGrpc.newBlockingStub(channel);
		NekoOcenioSmestajRequest rqst = NekoOcenioSmestajRequest.newBuilder().setIdKorisnika(os.getGost()).setIdSmestaja(os.getSmestaj()).build();
		NekoOcenioSmestajResponse rspns = bs.newRankSmestaj(rqst);
		if(rspns.getResult()) {
			System.out.println("USPESNO POSLATO OBAVESTENJE O NOVOJ OCENI SMESTAJA");
		}
	}

	@Autowired
	private Neo4jTemplate neo4jTemplate;
	@Autowired
	private Neo4jClient neo4jClient;
	
	public List<SmestajDTO> getRecommended(String userId) {
//		
//		String cypher = String.format("MATCH (k:Korisnik {id:'%s'})-[:REZERVISE]->(s1:Smestaj)\n" +
//		        "WITH collect(s1.id) AS reservedSmestaji\n" +
//		        "MATCH (o:Korisnik)-[:REZERVISE]->(s2:Smestaj)\n" +
//		        "WHERE s2.id IN reservedSmestaji AND o.id <> '%s'\n" +
//		        "WITH collect(s2.id) AS similarSmestaji\n" +
//		        "MATCH (s3:Smestaj)\n" +
//		        "WHERE s3.id IN similarSmestaji AND s3.vlasnik_id <> '%s'\n" +
//		        "AND NOT EXISTS {\n" +
//		        "  MATCH (s4:Smestaj)<-[:OCENJUJE]-(:Korisnik {id:'%s'}) \n" +
//		        "  WHERE s4.id IN similarSmestaji AND s4.id <> s3.id AND s4.vlasnik_id <> '%s'\n" +
//		        "}\n" +
//		        "WITH s3, similarSmestaji\n" +
//		        "MATCH (s3)<-[rat:OCENJUJE]-()\n" +
//		        "WHERE datetime(rat.datumIVreme).year = 2021\n" +
//		        "WITH s3, similarSmestaji, AVG(toFloat(rat.ocena)) AS smestajOcena\n" +
//		        "WITH s3, smestajOcena,\n" +
//		        "  REDUCE(sum = 0.0, i IN [n IN similarSmestaji WHERE n <> s3.id] |\n" +
//		        "    sum + CASE WHEN EXISTS((:Smestaj {id: i})<-[:REZERVISE]-(:Korisnik {id: '%s'})<-[:OCENJUJE]-())\n" +
//		        "      THEN 1.0/size((:Smestaj {id: i})<-[:REZERVISE]-(:Korisnik {id: '%s'})<-[:OCENJUJE]-()) ELSE 0 END\n" +
//		        "  ) AS similarity\n" +
//		        "WHERE similarity >= 0.2\n" +
//		        "RETURN s3.id AS smestajId, smestajOcena + similarity AS ukupnaOcena\n" +
//		        "ORDER BY ukupnaOcena DESC\n" +
//		        "LIMIT 10", userId, userId, userId, userId, userId, userId, userId);

		String cypher = String.format("MATCH (k:Korisnik)-[:REZERVISE]->(s:Smestaj)<-[rat:OCENJUJE]-(o:Korisnik)\n" +
		        "WHERE k.id = '%s' AND o.id <> '%s'\n" +
		        "WITH s, collect(DISTINCT rat.sme_taj_id) AS otherAccommodationIds\n" +
		        "MATCH (s2:Smestaj)\n" +
		        "WHERE s2.id IN otherAccommodationIds\n" +
		        "WITH s, s2, size(otherAccommodationIds) AS numSimilarUsers, avg(toFloat(s2.prosecnaOcena)) AS avgRating, otherAccommodationIds\n" +
		        "WHERE numSimilarUsers >= 2 AND avgRating >= 3\n" +
		        "MATCH (s)<-[:REZERVISE]-(o)-[r:OCENJUJE]->(s2)\n" +
		        "WHERE s2.id IN otherAccommodationIds AND o.id <> '%s'\n" +
		        "AND datetime(r.datumIVreme).month >= datetime().month - 3 AND r.ocena >= 3\n" +
		        "WITH s, count(*) AS numGoodRatings, numSimilarUsers, avgRating\n" +
		        "WHERE numGoodRatings > 5\n" +
		        "WITH s, (toFloat(numGoodRatings) / toFloat(numSimilarUsers)) AS similarity, avgRating, numSimilarUsers\n" +
		        "RETURN s.id AS smestajId, similarity + avgRating AS ukupnaOcena\n" +
		        "ORDER BY ukupnaOcena DESC\n" +
		        "LIMIT 10", userId, userId, userId);

		
//		String cypher = String.format("MATCH (u1:Korisnik {id:'%s'})-[:REZERVISE]->(a:Smestaj)" +
//			    "<-[:REZERVISE]-(u2:Korisnik)-[r1:OCENJUJE]->(a) " +
//			    "WITH u1, u2, a, AVG(r1.ocena) AS avg_rating, r1 " +
//			    "MATCH (u1)-[:OCENJUJE]->(a)<-[r2:OCENJUJE]-(u2) " +
//			    "WITH u1, u2, a, avg_rating, r1, r2 " +
//			    "WHERE u1 <> u2 AND abs(dateTime(r2.datumIVreme).epochSeconds " +
//			    "   - dateTime(r2.datumIVreme).epochSeconds) < 2592000000 " +
//			    "WITH u1, u2, collect(a) AS common_accommodations, avg_rating "
//			    + "RETURN common_accommodations", userId); 
		
		
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
				Collection<InternalNode> tmpList = (Collection<InternalNode>) value;
				for(InternalNode in : tmpList) {
					Value idSmestaja = in.get("id");
					String idSme = idSmestaja.toString();
					idSme = idSme.substring(1, idSme.length() - 1);
					if(idSme != null) {
						Smestaj acc = this.smestajRep.findById(idSme).orElse(null);
						if(acc != null) {
							retList.add(smestajMapper.toDTO(acc));
						}
						
					}
				}
				
			}
		}
		return retList;
	}
}
