package com.xml2023.smestajmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xml2023.smestajmicroservice.dtos.SmestajDTO;

import io.micrometer.core.instrument.MeterRegistry;

@Component
public class MetrikeMetode {
	@Autowired MeterRegistry mReg;

	public void createNewMetrike(ResponseEntity<SmestajDTO> resp, String string) throws JsonProcessingException {
		ObjectMapper o= new ObjectMapper();
		o.registerModule(new JavaTimeModule());
		String os= o.writeValueAsString(resp);
		int byteSize= os.getBytes().length;
		mReg.counter("http_response_size_bytes",
				"method","POST", 
				"status", resp.getStatusCode().toString(), 
				"endpoint", string, 
				"size", Integer.toString(byteSize)).increment(byteSize);
	}
}
