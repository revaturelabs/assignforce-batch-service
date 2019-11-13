package com.revature.testing.revaturepro;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.config.H2Config;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@Ignore
@RunWith(SpringRunner.class)
@ContextConfiguration(classes={RpBatchService.class, H2Config.class})
public class RevatureProH2InsertTest {

	@Autowired
	RpBatchService rpBatchRepository;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void revatureProH2InsertTest() throws Exception {

		List<Batch> result = rpBatchRepository.findAll();

		assertTrue("The list that was returned was not empty", result.isEmpty()); }

}
