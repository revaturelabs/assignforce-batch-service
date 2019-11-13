package com.revature.testing.revaturepro;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.service.RpBatchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= RpBatchService.class)

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
