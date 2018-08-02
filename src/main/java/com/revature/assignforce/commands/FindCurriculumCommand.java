package com.revature.assignforce.commands;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.assignforce.beans.Batch;

@Component
public class FindCurriculumCommand {

	@Value("")
	private String curriculumGatewayURL;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@HystrixCommand(fallbackMethod = "findCurriculumFallback")
	public Batch findCurriculum(Batch batch) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8765/curriculum-service/" + batch.getCurriculum(), String.class);
		return batch;
	}
	
	public Batch findCurriculumFallback(Batch batch) {
		batch.setCurriculum(null);
		return batch;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	
	
}
