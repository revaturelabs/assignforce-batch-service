package com.revature.assignforce.service;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.RevatureProBatchDTO;
import com.revature.assignforce.beans.RevatureProUserDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class RevatureProService {

    RevatureProBatchDTO allBatches;

    RevatureProUserDTO authenticatedUser;

    void authenticate(){

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RevatureProRestClient revatureProRestClient = new RevatureProRestClient(restTemplateBuilder);

        ResponseEntity<RevatureProUserDTO> user = revatureProRestClient.authenticate();

        this.authenticatedUser = user.getBody();
    }

    void getRevProBatches(){

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RevatureProRestClient revatureProRestClient = new RevatureProRestClient(restTemplateBuilder);
        ResponseEntity<RevatureProBatchDTO> batches = revatureProRestClient.getBatches();

        this.allBatches = batches.getBody();

    }

    Boolean batchesToDatabase(){

        return true;
    }




}
