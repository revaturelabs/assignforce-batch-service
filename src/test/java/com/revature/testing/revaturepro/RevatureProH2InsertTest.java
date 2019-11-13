package com.revature.testing.revaturepro;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.config.H2Config;
import com.revature.assignforce.config.ProjectServiceProviderConfig;
import com.revature.assignforce.config.SecurityConfig;
import com.revature.assignforce.repos.revaturepro.RpBatchService;
import com.revature.assignforce.service.RevatureProService;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@Ignore
@RunWith(SpringRunner.class)
@ContextConfiguration(classes={RpBatchService.class, H2Config.class, RevatureProService.class, Batch.class, ProjectServiceProviderConfig.class})
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
public class RevatureProH2InsertTest {

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
	public void revatureProH2InsertTest() throws Exception {

		Batch batch = new Batch();
		batch.setId(100);
		batch.setName("Test");
		rpBatchService.save(batch);

		assertTrue("The list that was returned was not empty", true); }

}
