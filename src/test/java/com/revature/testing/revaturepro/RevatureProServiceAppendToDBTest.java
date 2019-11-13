package com.revature.testing.revaturepro;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.config.H2Config;
import com.revature.assignforce.service.RpBatchService;

import com.revature.assignforce.service.RevatureProService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.Query;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(RevatureProService.class)
@SpringBootTest()
@ContextConfiguration(classes={RevatureProService.class, RpBatchService.class, SessionFactory.class, Batch.class, H2Config.class})

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

        Query query = (Query) session.createQuery("Select Count(Batch.id) from Batch");


        // assertEquals();

         }

}
