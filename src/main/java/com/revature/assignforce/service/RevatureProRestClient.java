package com.revature.assignforce.service;

import com.revature.assignforce.beans.revaturepro.RevatureProBatchDTO;
import com.revature.assignforce.beans.revaturepro.RevatureProUserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.util.List;


@Service()
public class RevatureProRestClient {

    private RestTemplate restTemplate;

    // @Value assigns a value to the variable from the resources/boostrap.yml file
    @Value("${revaturepro.username}")
    private String username;
    @Value("${revaturepro.password}")
    private String password;
    @Value("${revaturepro.urlAuthenticate}")
    private String urlAuthenticate;

    @Value("${revaturepro.urlGetBatches}")
    private String urlGetBatches;

    private String encryptedToken;

    public RevatureProRestClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }
    // RestClient for posting to the RevaturePro Authenticate API
    public ResponseEntity<RevatureProUserDTO> authenticate() {

        HttpEntity<RevatureProUserDTO> entity = new HttpEntity<>(new RevatureProUserDTO());

        entity.getBody().setUsername(username);
        entity.getBody().setPassword(password);

        ResponseEntity<RevatureProUserDTO> response= restTemplate.postForEntity(urlAuthenticate, entity, RevatureProUserDTO.class);

        // Save the authentication token to a class variable to use when performing get requests
        this.encryptedToken = response.getBody().getToken();

        return response;
        }

    // RestClient for getting batch data from the RevaturePro Batch API
    public List<RevatureProBatchDTO> getBatches() {

        InetSocketAddress batchURL = new InetSocketAddress(urlGetBatches, 8080);

        // Create request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setHost(batchURL);
        headers.set("encryptedToken", this.encryptedToken);

        // Add headers to request (entity)
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity batches = restTemplate.getForEntity(urlGetBatches, RevatureProBatchDTO[].class);


        return (List<RevatureProBatchDTO>) batches.getBody();
    }

}




