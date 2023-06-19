//package com.xml2023.smestajmicroservice;
//
//import org.springframework.context.annotation.Bean;
//
//import io.micrometer.core.instrument.DistributionSummary;
//import io.micrometer.core.instrument.MeterRegistry;
//
//public class DistributionConfig {
//	@Bean
//    public DistributionSummary httpReqSizeDistribution(MeterRegistry registry) {
//        return DistributionSummary.builder("http.request.size")
//                .description("Distribution of HTTP Request Size (Bytes)")
//                .baseUnit("bytes")
//                .register(registry);
//    }
//
//    @Bean
//    public DistributionSummary httpResSizeDistribution(MeterRegistry registry) {
//        return DistributionSummary.builder("http.response.size")
//                .description("Distribution of HTTP Response Size (Bytes)")
//                .baseUnit("bytes")
//                .register(registry);
//    }
//}
