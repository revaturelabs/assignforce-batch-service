package com.revature.assignforce.service;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.revaturepro.RevatureProBatchDTO;
import com.revature.assignforce.beans.revaturepro.RevatureProData;
import com.revature.assignforce.beans.revaturepro.RevatureProUserDTO;
import com.revature.assignforce.repos.revaturepro.RpBatchService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevatureProService {

    @Autowired
    RpBatchService rpBatchService;

    @Autowired
    private SessionFactory sessionFactory;

    List<RevatureProBatchDTO> allBatches;

    List<Batch> RDSBatches;

    void authenticate(){

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RevatureProRestClient revatureProRestClient = new RevatureProRestClient(restTemplateBuilder);

        ResponseEntity<RevatureProUserDTO> user = revatureProRestClient.authenticate();
    }

    void getBatches(){

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RevatureProRestClient revatureProRestClient = new RevatureProRestClient(restTemplateBuilder);

        // Call the getBatches method from revatureProRestClient and pass in the authentication token
        List<RevatureProBatchDTO> batches = revatureProRestClient.getBatches();

        // assign to class field
        this.allBatches = batches;

    }

    void RDSbatchesToDatabase(){
        for (Batch batch: this.RDSBatches) {
            rpBatchService.save(batch);
        }
    }

    public List<Batch> getRDSBatches() {
        return RDSBatches;
    }

    public void setRDSBatches(List<Batch> RDSBatches) {
        this.RDSBatches = RDSBatches;
    }
}
