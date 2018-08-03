package com.revature.assignforce.commands;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.assignforce.beans.Batch;

@Component
public class FindCurriculumCommand {

	@Value("${environment.gateway-url:http://localhost:8765/}")
	private String gatewayUrl;
	@Value("${environment.service.curriculum:curriculum-service/}")
	private String curriculumUri;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * Command called to verify that the batch curriculum provided exists in the curriculum service
	 * @param batch - new batch trying to be created
	 * @return - if the curriculum exists, return an unmodified batch
	 */
	@HystrixCommand(fallbackMethod = "findCurriculumFallback")
	public Batch findCurriculum(Batch batch) {
		ResponseEntity<String> response = restTemplate.getForEntity(gatewayUrl + curriculumUri + batch.getCurriculum(), String.class);
		return batch;
	}
	
	/**
	 * Fallback method in case the curriculum does not exist
	 * @param batch - new batch to be created
	 * @return - if the curriculum is not found, sets curriculum to null
	 */
	public Batch findCurriculumFallback(Batch batch) {
		batch.setCurriculum(null);
		return batch;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	
	
}
