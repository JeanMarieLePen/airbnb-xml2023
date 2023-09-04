package com.example.reservationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.micrometer.core.instrument.MeterRegistry;

@Component
public class MetrikeMetode {
	@Autowired MeterRegistry mReg;
	public void incrementTotalResponse(Object resp, String endpoint, String method, int status) throws JsonProcessingException {
		ObjectMapper o= new ObjectMapper();
		o.registerModule(new JavaTimeModule());
		String os= o.writeValueAsString(resp);
		int byteSize= os.getBytes().length;
		mReg.counter("http_response_size_bytes",
				"method",method, 
				"status", String.valueOf(status),
				"endpoint", endpoint//, 
				//"size", Integer.toString(byteSize)
				).increment(byteSize);
		
		mReg.counter("http_response_count_total", 
				"endpoint", endpoint,
				"status", String.valueOf(status)
				).increment(1);
	}
}
