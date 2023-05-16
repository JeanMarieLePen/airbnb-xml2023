package com.xml.mainapp.mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml.mainapp.dtos.user.HostDTO;
import com.xml.mainapp.dtos.user.OcenaHostDTO;
import com.xml.mainapp.model.data.Smestaj;
import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.model.users.OcenaHost;
import com.xml2023.mainapp.SmestajDTO;
import com.xml2023.mainapp.SmestajGrpcGrpc;
import com.xml2023.mainapp.SmestajGrpcGrpc.SmestajGrpcBlockingStub;
import com.xml2023.mainapp.getListaSmestajaByUserIdRequest;
import com.xml2023.mainapp.getListaSmestajaByUserIdResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class HostMapper {

	@Autowired
	private AdresaMapper aMapper;
	@Autowired
	private OcenaHostMapper ohMapper;
	@Autowired
	private SmestajBasicMapper smestajMapper;
	
	public Host fromDTO(HostDTO dto) {
		Host h = new Host();
		h.setAdresa(aMapper.fromDTO(dto.getAdresa()));
		h.setEmail(dto.getEmail());
		h.setIme(dto.getIme());
		h.setPrezime(dto.getPrezime());
		h.setKorIme(dto.getKorIme());
//		h.setId(dto.getId());
		h.setLozinka(dto.getLozinka());
		h.setTipKorisnika(dto.getTipKorisnika());
		
		Collection<byte[]> tempSlike = new ArrayList<byte[]>();
		for(String s: dto.getSlike()) {
			byte[] data = Base64.getDecoder().decode(s.split(",")[1]);
			tempSlike.add(data);
		}
		h.setSlike(tempSlike);
		Collection<OcenaHost> tempOcene = new ArrayList<OcenaHost>();
		if(dto.getOcene()!=null) {
			for(OcenaHostDTO o: dto.getOcene()) {
				tempOcene.add(ohMapper.fromDTO(o));
			}
		}
		//h.setOcene(tempOcene);
		
		/*
		 * Collection<Smestaj> tempSmestaj = new ArrayList<Smestaj>();
		 * if(dto.getSmestajList() != null) { for(SmestajDTO s:dto.getSmestajList()) {
		 * tempSmestaj.add(smestajMapper.fromDTO(s)); } }
		 */
		
		
		
		h.setStatusNaloga(dto.getStatusNaloga());
		
		h.setIstaknuti(dto.isIstaknuti());
		h.setRezAutomatski(dto.isRezAutomatski());
		
		return h;
	}
	
	public HostDTO toDTO(Host h, Collection<SmestajDTO> smestajList) {
		HostDTO dto = new HostDTO();
		dto.setAdresa(aMapper.toDTO(h.getAdresa()));
		dto.setEmail(h.getEmail());
		dto.setIme(h.getIme());
		dto.setPrezime(h.getPrezime());
		dto.setKorIme(h.getKorIme());
		dto.setId(h.getId());
		dto.setLozinka(h.getLozinka());
		dto.setTipKorisnika(h.getTipKorisnika());
		
		dto.setIstaknuti(h.isIstaknuti());
		dto.setRezAutomatski(h.isRezAutomatski());
		
		if(h.getSlike() != null) {
			Collection<String> tempSlike = new ArrayList<String>();
			for(byte[] s : h.getSlike()) {
				String tempSlika = Base64.getEncoder().encodeToString(s);
				tempSlike.add(tempSlika);
			}
			dto.setSlike(tempSlike);
		}
				
		dto.setSmestajList(smestajList);
		
		/*
		 * if(h.getSmestajList() != null) { for(Smestaj tmp : h.getSmestajList()) {
		 * smestajList.add(smestajMapper.toDTO(tmp)); } }
		 * dto.setSmestajList(smestajList);
		 */
		
		
		//ocene hosta, kopirati ako zatreba negde
		/*
		 * Collection<OcenaHostDTO> tempOceneHost = new ArrayList<OcenaHostDTO>();
		 * if(h.getOcene()!=null) { for(OcenaHost o: h.getOcene()) {
		 * tempOceneHost.add(ohMapper.toDTO(o)); } }
		 *dto.setOcene(tempOceneHost);
		 **/
		dto.setOcene(new ArrayList<OcenaHostDTO>());
		dto.setProsecnaOcena(0);
		return dto;
	}
	
	public int calcOcena(Collection<OcenaHost> ocene){
		 int retVal = ocene.stream().mapToInt(OcenaHost::getOcena).sum();
		 return retVal;
	}
	
	//trebace za kasnije
	public HostDTO toDTOLight(Host h) {
		return null;
	}
}
