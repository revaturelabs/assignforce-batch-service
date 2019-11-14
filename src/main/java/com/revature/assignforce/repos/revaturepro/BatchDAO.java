package com.revature.assignforce.repos.revaturepro;

import com.revature.assignforce.beans.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;


public class BatchDAO {

    @Autowired
    RpBatchService batchRepository;

    public List<Batch> getAllPersons() {
        List<Batch> batches = new ArrayList<Batch>();
        batchRepository.findAll().forEach(batch -> batches.add(batch));
        return batches;
    }

}
