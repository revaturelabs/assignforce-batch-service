package com.revature.assignforce.service;

import com.revature.assignforce.beans.revaturepro.RevatureProBatchDTO;
import com.revature.assignforce.beans.revaturepro.RevatureProUserDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;

public class RevatureProService {

    RevatureProBatchDTO allBatches;

    void authenticate(){

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RevatureProRestClient revatureProRestClient = new RevatureProRestClient(restTemplateBuilder);

        ResponseEntity<RevatureProUserDTO> user = revatureProRestClient.authenticate();
    }

    void getBatches(){

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RevatureProRestClient revatureProRestClient = new RevatureProRestClient(restTemplateBuilder);

        // Call the getBatches method from revatureProRestClient and pass in the authentication token
        ResponseEntity<RevatureProBatchDTO> batches = revatureProRestClient.getBatches();

        this.allBatches = batches.getBody();

    }

    Boolean batchesToDatabase(){

        return true;
    }




}
