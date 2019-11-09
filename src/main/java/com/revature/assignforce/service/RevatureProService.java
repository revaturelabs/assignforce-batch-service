package com.revature.assignforce.service;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.RevatureProUserDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.ArrayList;
import java.util.List;

public class RevatureProService {

    ArrayList<Batch> allBatches;

    RevatureProUserDTO authenticate(){

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RevatureProRestClient revatureProRestClient = new RevatureProRestClient(restTemplateBuilder);
        RevatureProUserDTO user = new RevatureProUserDTO();

        return user;
    }

    ArrayList<Batch> getRevProBatches(){

        return null;
    }

    Boolean batchesToDatabase(){

        return true;
    }




}
