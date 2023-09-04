package com.gatewayservice.eurekagateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.micrometer.core.instrument.MeterRegistry;

@Component
public class MetrikeMetode {
	@Autowired MeterRegistry mReg;

	public void incrementTotalResponse(Object resp, String endpoint, String method) throws JsonProcessingException {
		ObjectMapper o= new ObjectMapper();
		o.registerModule(new JavaTimeModule());
		String os= o.writeValueAsString(resp);
		int byteSize= os.getBytes().length;
		
		mReg.counter("http_request_size_bytes",
				"method",method, 
				"endpoint", endpoint//, 
				//"size", Integer.toString(byteSize)
				).increment(byteSize);
		
		mReg.counter("http_request_count_total", 
				"endpoint", endpoint
				).increment(1);

	}
	public void trackVisitors(String id, String userAgent, String timestamp ) {
		mReg.counter("UNIQUE_VISITS",
				"ip", id,
				"userAgent", userAgent,
				"timestamp", timestamp
				).increment(1);
	}
}
