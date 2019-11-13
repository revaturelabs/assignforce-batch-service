package com.revature.assignforce.service;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.revaturepro.RevatureProBatchDTO;
import com.revature.assignforce.beans.revaturepro.RevatureProData;
import com.revature.assignforce.beans.revaturepro.RevatureProUserDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        int counter = (int) Math.random()*1000;

        for ( RevatureProBatchDTO batch: this.allBatches) {

            // Parse info
            RevatureProData data = (RevatureProData) batch.getData();

            Session session = sessionFactory.getCurrentSession();

            Batch abatch = new Batch();
            abatch.setId(counter);
            // abatch.setId(Integer.parseInt(data.getSalesforceId()));
            abatch.setName(data.getName());
            abatch.setStartDate(LocalDate.parse(data.getStartDate().substring(0, 10)));
            abatch.setEndDate(LocalDate.parse(data.getEndDate().substring(0, 10)));
            // abatch.setCurriculum();
            // abatch.setTrainer();
            // abatch.setCoTrainer();
            // abatch.setSkills(data.getSkill());
            abatch.setLocation(Integer.parseInt(data.getLocation().substring(3,5))); // It's on you guys if this doesn't work
            // abatch.setType <- No such thing in Batch

            counter++;

            session.save(abatch);
            session.getTransaction().commit();
            sessionFactory.close();

        }

    }

    public List<Batch> getRDSBatches() {
        return RDSBatches;
    }

    public void setRDSBatches(List<Batch> RDSBatches) {
        this.RDSBatches = RDSBatches;
    }
}
