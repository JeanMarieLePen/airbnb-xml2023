package com.xml.mainapp;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class MetrikeConfig {
//	@Bean
//	public MeterRegistry getMeterRegistry() {
//		CompositeMeterRegistry meterRegistry = new CompositeMeterRegistry();        
//        return meterRegistry;
//	}
	@Bean
	public MeterRegistryCustomizer<MeterRegistry> addCustomTags(){

		 return (registry -> registry.config().commonTags("region", "srb").commonTags("service","reglog"));
	}
}
