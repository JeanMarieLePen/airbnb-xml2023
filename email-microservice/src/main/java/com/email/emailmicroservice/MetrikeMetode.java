package com.email.emailmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.micrometer.core.instrument.MeterRegistry;

@Component
public class MetrikeMetode {
	@Autowired MeterRegistry mReg;
	public void incrementTotalResponse(Object resp)  throws JsonProcessingException {
		ObjectMapper o= new ObjectMapper();
		o.registerModule(new JavaTimeModule());
		String os= o.writeValueAsString(resp);
		int byteSize= os.getBytes().length;
		mReg.counter("http_response_size_bytes",
				"method","SendMessage", 
				"status", "0",
				"endpoint", "/"//, 
				//"size", Integer.toString(byteSize)
				).increment(byteSize);
		
		mReg.counter("http_response_count_total", 
				"endpoint", "/",
				"status", "0"
				).increment(1);
	}
}
