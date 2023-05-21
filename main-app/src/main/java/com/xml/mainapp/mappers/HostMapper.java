package com.xml.mainapp.mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml.mainapp.dtos.user.HostDTO;
import com.xml.mainapp.dtos.user.OcenaHostDTO;
import com.xml.mainapp.model.users.Host;
import com.xml.mainapp.model.users.OcenaHost;
import com.xml2023.mainapp.SmestajDTO;

@Component
public class HostMapper {

	@Autowired
	private AdresaMapper aMapper;
	
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
		h.setCanceledNotification(dto.isCanceledNotification());
		h.setNewNotification(dto.isNewNotification());
		h.setRatedHostNotification(dto.isRatedHostNotification());
		h.setRatedAccomodationNotification(dto.isRatedAccomodationNotification());
		h.setStatusNotification(dto.isStatusNotification());
		
		
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
		dto.setStatusNaloga(h.getStatusNaloga());
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
		
		dto.setCanceledNotification(h.isCanceledNotification());
		dto.setNewNotification(h.isNewNotification());
		dto.setRatedHostNotification(h.isRatedHostNotification());
		dto.setRatedAccomodationNotification(h.isRatedAccomodationNotification());
		dto.setStatusNotification(h.isStatusNotification());
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
