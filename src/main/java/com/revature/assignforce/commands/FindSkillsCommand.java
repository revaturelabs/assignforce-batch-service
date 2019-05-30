package com.revature.assignforce.commands;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.assignforce.beans.SkillIdHolder;

@Component
public class FindSkillsCommand {

	@Value("${environment.gateway-url:http://localhost:8765/}")
	private String gatewayUrl;
	@Value("${environment.service.skills:skill-service/}")
	private String skillUri;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * Command called to verify that the skill provided exists in the skill service
	 * @param skillIdHolder - skill id
	 * @return - if the skill exists, return true
	 */
	@HystrixCommand(fallbackMethod = "findSkillFallback")
	public boolean findSkill(SkillIdHolder skillIdHolder) {
		ResponseEntity<String> response = restTemplate.getForEntity(gatewayUrl + skillUri + skillIdHolder.getId(), String.class);
		return true;
	}
	
	/**
	 * Fallback method in case the skill does not exist
	 * @param skillIdHolder - skill id
	 * @return - if the skill is not found, return false
	 */
	public boolean findSkillFallback(SkillIdHolder skillIdHolder) {
		return false;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
}
