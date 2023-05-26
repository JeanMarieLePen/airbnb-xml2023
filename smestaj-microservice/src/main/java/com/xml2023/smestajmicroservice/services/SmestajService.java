package com.xml2023.smestajmicroservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.xml2023.smestajmicroservice.dtos.OcenaSmestajaDTO;
import com.xml2023.smestajmicroservice.dtos.SmestajDTO;
import com.xml2023.smestajmicroservice.mappers.SmestajBasicMapper;
import com.xml2023.smestajmicroservice.model.data.OcenaSmestaj;
import com.xml2023.smestajmicroservice.model.data.Pogodnost;
import com.xml2023.smestajmicroservice.model.data.Smestaj;
import com.xml2023.smestajmicroservice.neo4j.repositories.Neo4JSmestajRep;
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
	
	public SmestajDTO createNew(SmestajDTO s) {
		//u maperu se definisu i cuvaju svi objekti koji su ugnjezdeni u objekat smestaj
		Smestaj tmp = smestajMapper.fromDTO(s);
		this.smestajRep.save(tmp);
		this.smestajNeoRep.save(tmp);
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
			o=new OcenaSmestaj( smestajId, userId, ocena.getOcena(), LocalDate.now());
		}else {
			o.setDatum(LocalDate.now());
			o.setOcena(ocena.getOcena());
		}
		ocenaRep.save(o);
		
		if(novaOcenaSmestajaNotificationEnabled(smestajId)) {
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
}
