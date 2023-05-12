package com.xml.mainapp;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xml.mainapp.services.GreetingServiceImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {

	private final int port;
	private final Server server;
	public GrpcServer(int port) throws IOException {
	    this.port = port;
	    this.server = ServerBuilder.forPort(port)
	        .addService(new GreetingServiceImpl())
	        .build();
	}
	public void start() throws IOException {
	    server.start();
	    System.out.println("gRPC server started, listening on port " + port);
	    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	      System.out.println("Shutting down gRPC server");
	      GrpcServer.this.stop();
	    }));
	  }
	 public void stop() {
		    if (server != null) {
		      server.shutdown();
		    }
		  }
	 public void blockUntilShutdown() throws InterruptedException {
		    if (server != null) {
		      server.awaitTermination();
		    }
		  }
}
