package com.revature.assignforce.commands;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.SkillIdHolder;

@Component
public class FindSkillsCommand {

	@Value("${environment.gateway-url:http://localhost:8765/}")
	private String gatewayUrl;
	@Value("${environement.service.skills:skill-service/}")
	private String skillUri;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@HystrixCommand(fallbackMethod = "findSkillFallback")
	public boolean findSkill(SkillIdHolder skillIdHolder) {
		ResponseEntity<String> response = restTemplate.getForEntity(gatewayUrl + skillUri + skillIdHolder.getSkillId(), String.class);
		return true;
	}
	
	public boolean findSkillFallback(SkillIdHolder skillIdHolder) {
		return false;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
}
