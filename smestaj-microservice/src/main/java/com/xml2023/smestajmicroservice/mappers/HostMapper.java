package com.xml2023.smestajmicroservice.mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml2023.smestajmicroservice.dtos.HostDTO;
import com.xml2023.smestajmicroservice.dtos.OcenaHostDTO;
import com.xml2023.smestajmicroservice.dtos.SmestajDTO;
import com.xml2023.smestajmicroservice.model.data.Host;
import com.xml2023.smestajmicroservice.model.data.OcenaHost;
import com.xml2023.smestajmicroservice.model.data.Smestaj;


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
		h.setOcene(tempOcene);
		
		Collection<Smestaj> tempSmestaj = new ArrayList<Smestaj>();
		if(dto.getSmestajList() != null) {
			for(SmestajDTO s:dto.getSmestajList()) {
				tempSmestaj.add(smestajMapper.fromDTO(s));
			}
		}
		
		
		
		h.setStatusNaloga(dto.getStatusNaloga());
		
		h.setIstaknuti(dto.isIstaknuti());
		h.setRezAutomatski(dto.isRezAutomatski());
		
		return h;
	}
	
	public HostDTO toDTO(Host h) {
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
		
		Collection<SmestajDTO> smestajList = new ArrayList<SmestajDTO>();
		if(h.getSmestajList() != null) {
			for(Smestaj tmp : h.getSmestajList()) {
				smestajList.add(smestajMapper.toDTO(tmp));
			}
		}
		dto.setSmestajList(smestajList);
		
		//ocene hosta, kopirati ako zatreba negde
		Collection<OcenaHostDTO> tempOceneHost = new ArrayList<OcenaHostDTO>();
		if(h.getOcene()!=null) {
			for(OcenaHost o: h.getOcene()) {
				tempOceneHost.add(ohMapper.toDTO(o));
			}
		}
		dto.setOcene(tempOceneHost);
		
		Collection<String> tempSlike = new ArrayList<String>();
		for(byte[] s : h.getSlike()) {
			String tempSlika = Base64.getEncoder().encodeToString(s);
			tempSlike.add(tempSlika);
		}
		dto.setSlike(tempSlike);
		dto.setProsecnaOcena(calcOcena(h.getOcene()));
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