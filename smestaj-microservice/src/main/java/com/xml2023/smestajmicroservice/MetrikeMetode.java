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
	
	public void grpcRequestExport(int req, String method) throws JsonProcessingException {
		
		int byteSize= req;
		mReg.counter("grpc_request_count",
				"method",method
				//"size", Integer.toString(byteSize)
				).increment(1);
		mReg.counter("grpc_request_byte_total",
				"method",method
				//"size", Integer.toString(byteSize)
				).increment(byteSize);
	}
	public void grpcResponseExport(int req, String method) throws JsonProcessingException {
		int byteSize= req;

		mReg.counter("grpc_response_count",
				"method",method
				//"size", Integer.toString(byteSize)
				).increment(1);
		mReg.counter("grpc_response_byte_total",
				"method",method
				//"size", Integer.toString(byteSize)
				).increment(byteSize);
	}
}
