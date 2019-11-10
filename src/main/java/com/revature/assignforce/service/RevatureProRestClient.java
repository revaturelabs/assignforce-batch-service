package com.revature.assignforce.service;

import com.revature.assignforce.beans.RevatureProBatchDTO;
import com.revature.assignforce.beans.RevatureProUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;


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

    private String encryptedToken;

    public RevatureProRestClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }
    // RestClient for posting to the RevaturePro Authenticate API
    public ResponseEntity<RevatureProUserDTO> authenticate() {

        HttpEntity<RevatureProUserDTO> entity = new HttpEntity<>(new RevatureProUserDTO());

        entity.getBody().setUsername(username);
        entity.getBody().setPassword(password);

        return restTemplate.postForEntity(url, entity, RevatureProUserDTO.class);
        }

    // RestClient for getting batch data from the RevaturePro Batch API
    public ResponseEntity<RevatureProBatchDTO> getBatches(String encryptedToken) {

        this.encryptedToken = encryptedToken;

        InetSocketAddress batchURL = new InetSocketAddress("url", 8080);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setHost(batchURL);
        headers.set("encryptedToken", encryptedToken);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        return restTemplate.getForEntity(url, RevatureProBatchDTO.class);
    }

}




