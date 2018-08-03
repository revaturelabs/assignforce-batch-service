package com.revature.assignforce.commands;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.assignforce.beans.Batch;

@Component
public class FindLocationCommand {

	@Value("${environment.gateway-url:http://localhost:8765/}")
	private String gatewayUrl;
	@Value("${environement.service.location:location-service/}")
	private String locationUri;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@HystrixCommand(fallbackMethod = "findLocationFallback")
	public Batch findLocation(Batch batch) {
		ResponseEntity<String> response = restTemplate.getForEntity(gatewayUrl + locationUri + batch.getLocation(), String.class);
		return batch;
	}
	
	public Batch findLocationFallback(Batch batch) {
		batch.setLocation(null);
		return batch;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	
	
}
