package com.revature.assignforce.service;

import com.revature.assignforce.beans.SprintDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class GitHubProjectServiceProvider implements IssuesServiceProvider {

    private String apiKey;
    private RestTemplate restTemplate;
    private String apiUrl = "https://api.github.com/repos/revaturelabs/{repo}/projects";

    public GitHubProjectServiceProvider() {}

    public void setApiKey(String key){
        this.apiKey = key;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<SprintDTO> getNativeApiSprints(String name) {
        String url = apiUrl.replace("{repo}", name);
        return restTemplate.exchange(url,
                HttpMethod.GET,
                requestEntity(),
                new ParameterizedTypeReference<List<SprintDTO>>() {
                }).getBody();
    }

    private HttpEntity<String> requestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github.inertia-preview+json");
        headers.add("Authorization", "Bearer " + apiKey);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        return entity;
    }

}
