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

    void RevatureProDatabaseInsert(){

        for ( RevatureProBatchDTO batch: this.allBatches) {
            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("insert into Batch (Batch_ID, Batch_Name, start_Date, end_Date, Curriculum_Id, Trainer_Id, Cotrainer_Id, Batch_ID, Skill_ID, LOCATION_ID, BUILDING_ID, ROOM_ID, Class_Size)")

            // Parse info
            RevatureProData data = (RevatureProData) batch.getData();

            // Set parameters
            query.setParameter("Batch_ID", data.getSalesforceId());

            int rowsCopied = query.executeUpdate();
        }

    }

    public List<Batch> getRDSBatches() {
        return RDSBatches;
    }

    public void setRDSBatches(List<Batch> RDSBatches) {
        this.RDSBatches = RDSBatches;
    }
}
