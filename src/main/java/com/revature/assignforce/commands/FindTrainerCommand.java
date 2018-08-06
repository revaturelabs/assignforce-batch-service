package com.revature.assignforce.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.assignforce.beans.Batch;

@Component
public class FindTrainerCommand {

	@Value("${environment.gateway-url:http://localhost:8765/}")
	private String gatewayUrl;
	@Value("${environment.service.trainer:trainer-service/}")
	private String trainerUri;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * Command called to verify that the batch trainer provided exists in the trainer service
	 * @param batch - new batch trying to be created
	 * @return - if the trainer exists, return an unmodified batch
	 */
	@HystrixCommand(fallbackMethod = "findTrainerFallback")
	public Batch findTrainer(Batch batch) {
		ResponseEntity<String> response = restTemplate.getForEntity(gatewayUrl + trainerUri + batch.getTrainer(), String.class);
		return batch;
	}
	
	/**
	 * Fallback method in case the trainer does not exist
	 * @param batch - new batch to be created
	 * @return - if the trainer is not found, sets trainer to null
	 */
	public Batch findTrainerFallback(Batch batch) {
		batch.setTrainer(null);
		return batch;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
}
