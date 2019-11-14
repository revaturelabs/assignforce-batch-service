package com.revature.assignforce.service;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.BatchH2;
import com.revature.assignforce.beans.revaturepro.RevatureProBatchDTO;
import com.revature.assignforce.beans.revaturepro.RevatureProData;
import com.revature.assignforce.beans.revaturepro.RevatureProUserDTO;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RevatureProService {


    List<RevatureProBatchDTO> allBatches;

    List<Batch> RDSBatches;
    List<Batch> RDSBatchesOld;

    public HttpStatus authenticate(){

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RevatureProRestClient revatureProRestClient = new RevatureProRestClient(restTemplateBuilder);

        ResponseEntity<RevatureProUserDTO> user = revatureProRestClient.authenticate();

        return user.getStatusCode();

    }

    public void getBatches(){

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RevatureProRestClient revatureProRestClient = new RevatureProRestClient(restTemplateBuilder);

        // Call the getBatches method from revatureProRestClient and pass in the authentication token
        List<RevatureProBatchDTO> batches = revatureProRestClient.getBatches();

        // assign to class field
        this.allBatches = batches;

    }

    public void AddToRDSBatch(Batch batch){

        if (RDSBatchesOld == null){ RDSBatchesOld = new ArrayList<Batch>(); }

        this.RDSBatchesOld.add(batch);

    }

    public void RevatureProDatabaseInsert() {
        for (int i = 0; i<this.allBatches.size();i++ ){

            // Parse info
            List<RevatureProData> data = this.allBatches.get(i).getData();

            System.out.println(data.size());
            System.out.println(data.get(0).getName());
            Batch abatch = new Batch();
            abatch.setId(Integer.parseInt(data.get(0).getSalesforceId()));
            abatch.setName(data.get(0).getName());
            abatch.setStartDate(LocalDate.parse(data.get(0).getStartDate().substring(0, 10)));
            abatch.setEndDate(LocalDate.parse(data.get(0).getEndDate().substring(0, 10)));

            System.out.println(data.get(0).getEndDate());
            System.out.println(data.get(0).getLocation());
            abatch.setLocation(Integer.parseInt(data.get(0).getLocation().substring(3, 5))); // It's on you guys if this doesn't work

            if (abatch == null){
                System.out.println(99999);
            }

            this.AddToRDSBatch(abatch);
            System.out.println(abatch.toString());
            System.out.println(this.RDSBatchesOld.size());

        }
    }

    public List<Batch> getRDSBatches() {
        return RDSBatches;
    }


    public void setRDSBatchesOld(List<Batch> RDSBatches) {
        this.RDSBatchesOld = RDSBatches;
    }

    public void setRDSBatches(List<Batch> RDSBatches) {
        this.RDSBatches = RDSBatches;
    }

    public static List<BatchH2> convertToBatchH2(List<Batch> batchList)  {

        List<BatchH2> h2List = new ArrayList<>();

        for (Batch abatch : batchList) {

            h2List.add(new BatchH2(abatch));
        }

        return h2List;

    }

    public List<RevatureProBatchDTO> getAllBatches() {
        return allBatches;
    }

    public void setAllBatches(List<RevatureProBatchDTO> allBatches) {
        System.out.println(allBatches.get(0).getData().get(0).getLocation());
        System.out.println("ASDFvefcwG");
        this.allBatches = allBatches;
    }

    public List<Batch> getRDSBatchesOld() {
        return RDSBatchesOld;
    }
}
