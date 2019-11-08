package com.revature.assignforce.service;

import com.revature.assignforce.beans.RevatureProUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service()
public class RevatureProRestClient {

    private RestTemplate restTemplate;

    // @Value assigns a value to the variable from the resources/boostrap.yml file
    @Value("${revaturepro.username}")
    private String username;
    @Value("${revaturepro.password}")
    private String password;
    @Value("${revaturepro.urlAuthenticate}")
    private String url;

    public RevatureProRestClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<RevatureProUserDTO> authenticate() {

        HttpEntity<RevatureProUserDTO> request = new HttpEntity<>(new RevatureProUserDTO());

        request.getBody().setUsername(username);
        request.getBody().setPassword(password);

        return restTemplate.postForEntity(url, request, RevatureProUserDTO.class);
        }
}




