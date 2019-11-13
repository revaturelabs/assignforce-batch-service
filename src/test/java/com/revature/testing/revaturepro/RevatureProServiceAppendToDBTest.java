package com.revature.testing.revaturepro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.revaturepro.RevatureProBatchDTO;
import com.revature.assignforce.beans.revaturepro.RevatureProUserDTO;
import com.revature.assignforce.repos.revaturepro.RpBatchService;
import com.revature.assignforce.service.BatchService;
import com.revature.assignforce.service.RevatureProRestClient;

import com.revature.assignforce.service.RevatureProService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.client.MockRestServiceServer;

import javax.management.Query;
import javax.sound.midi.Track;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@RestClientTest(RevatureProService.class)
@SpringBootTest(classes=RevatureProService.class)

public class RevatureProServiceAppendToDBTest {

    @Autowired
    private RevatureProService revatureProService;

    @Autowired
    private RpBatchService rpBatchService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private Batch batch;

    @Before
    public void setUp() throws Exception {

        Batch batch = new Batch();
        batch.setId(100);
        batch.setName("Test");
        rpBatchService.save(batch);

    }

    @Test
    public void revatureProServiceDatabaseInsert()
            throws Exception {

        batch.setId(101);
        batch.setName("Test1");
        rpBatchService.save(batch);

        Session session = sessionFactory.getCurrentSession();

        Query query = (Query) session.createQuery("Select Count(Batch_ID) from Batch");


        // assertEquals();

         }

}
