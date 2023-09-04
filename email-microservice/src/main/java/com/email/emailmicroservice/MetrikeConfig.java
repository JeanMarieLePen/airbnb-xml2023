package com.email.emailmicroservice;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class MetrikeConfig {

	@Bean
	public MeterRegistryCustomizer<MeterRegistry> addCustomTags(){

		 return (registry -> registry.config().commonTags("region", "srb").commonTags("service","email"));
	}
}

