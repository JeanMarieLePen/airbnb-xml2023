package com.gatewayservice.eurekagateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

	@GetMapping("/mainAppServiceFallBack")
	public String mainAppServiceFallBackMethod() {
		return "Service is taking longer than expected";
	}
}
