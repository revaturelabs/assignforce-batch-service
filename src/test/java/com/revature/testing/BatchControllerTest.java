package com.revature.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.controllers.BatchController;
import com.revature.assignforce.repos.BatchRepository;
import com.revature.assignforce.service.BatchService;
import com.revature.assignforce.service.BatchServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BatchControllerTest {

	@Configuration
	static class BatchServiceTestContextConfiguration {
	@Bean
	public BatchService batchService() {
		return new BatchServiceImpl();
		}
	@Bean
	public BatchRepository batchRepository() {
		return Mockito.mock(BatchRepository.class);
		}
	@Bean
	public BatchController batchController() {
		return new BatchController();
	}
	}
	
	@Autowired
	private BatchRepository batchRepository;
	@Autowired
	private BatchController batchController;
	
	@Test
	public void getAllTest() {
		SkillIdHolder s1 = new SkillIdHolder(1);
		SkillIdHolder s2 = new SkillIdHolder(2);
		SkillIdHolder s3 = new SkillIdHolder(3);
		SkillIdHolder s4 = new SkillIdHolder(4);
		SkillIdHolder s5 = new SkillIdHolder(5);
		HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
		skillSet.add(s1);
		skillSet.add(s2);
		skillSet.add(s3);
		skillSet.add(s4);
		skillSet.add(s5);
		Batch b1 = new Batch(1, "Microservices", new Date(1515733200000L), new Date(1520053200000L), 3, 6, 5, skillSet, 1, 1);
		Batch b2 = new Batch(2, "Salesforce", new Date(1517634000000L), new Date(1522209600000L), 3, 7, 3, skillSet, 2,3);
		Batch b3 = new Batch(4, "Database", new Date(1522728000000L), new Date(1527048000000L), 5, 3, 5, skillSet, 2, 1);
		List<Batch> batchList = new ArrayList<Batch>();
		batchList.add(b1);
		batchList.add(b2);
		batchList.add(b3);
		Mockito.when(batchRepository.findAll()).thenReturn(batchList);
		
		List<Batch> testList = batchController.getAll();
		assertTrue(testList.size() == 3);
	}
	
	@Test
	public void getByIdTestOk() {
		SkillIdHolder s1 = new SkillIdHolder(1);
		SkillIdHolder s2 = new SkillIdHolder(2);
		SkillIdHolder s3 = new SkillIdHolder(3);
		SkillIdHolder s4 = new SkillIdHolder(4);
		SkillIdHolder s5 = new SkillIdHolder(5);
		HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
		skillSet.add(s1);
		skillSet.add(s2);
		skillSet.add(s3);
		skillSet.add(s4);
		skillSet.add(s5);
		Batch b1 = new Batch(3, "Microservices", new Date(1515733200000L), new Date(1520053200000L), 3, 6, 5, skillSet, 1, 1);
		Optional<Batch> op1 = Optional.ofNullable(b1);
		Mockito.when(batchRepository.findById(3)).thenReturn(op1);
		ResponseEntity<Batch> reTest = batchController.getById(3);
		assertTrue(reTest.getBody().getId() == 3 && reTest.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void getByIdTestNotFound() {
		ResponseEntity<Batch> reTest = batchController.getById(6);
		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void addTestCreated() {
		SkillIdHolder s1 = new SkillIdHolder(1);
		SkillIdHolder s2 = new SkillIdHolder(2);
		SkillIdHolder s3 = new SkillIdHolder(3);
		SkillIdHolder s4 = new SkillIdHolder(4);
		SkillIdHolder s5 = new SkillIdHolder(5);
		HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
		skillSet.add(s1);
		skillSet.add(s2);
		skillSet.add(s3);
		skillSet.add(s4);
		skillSet.add(s5);
		Batch b1 = new Batch(5, "AWS", new Date(1515733200000L), new Date(1520053200000L), 3, 6, 5, skillSet, 1, 1);
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);
		ResponseEntity<Batch> reTest = batchController.add(b1);
		assertTrue(reTest.getBody().getId() == 5 && reTest.getStatusCode() == HttpStatus.CREATED);
	}
	
	@Test
	public void addTestBadRequest() {
		SkillIdHolder s1 = new SkillIdHolder(1);
		SkillIdHolder s2 = new SkillIdHolder(2);
		SkillIdHolder s3 = new SkillIdHolder(3);
		SkillIdHolder s4 = new SkillIdHolder(4);
		SkillIdHolder s5 = new SkillIdHolder(5);
		HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
		skillSet.add(s1);
		skillSet.add(s2);
		skillSet.add(s3);
		skillSet.add(s4);
		skillSet.add(s5);
		Batch b1 = new Batch(15, "Salesforce", new Date(1515733200000L), new Date(1520053200000L), 3, 6, 5, skillSet, 1, 1);
		ResponseEntity<Batch> reTest = batchController.add(b1);
		assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void updateTestOk() {
		SkillIdHolder s1 = new SkillIdHolder(1);
		SkillIdHolder s2 = new SkillIdHolder(2);
		SkillIdHolder s3 = new SkillIdHolder(3);
		SkillIdHolder s4 = new SkillIdHolder(4);
		SkillIdHolder s5 = new SkillIdHolder(5);
		HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
		skillSet.add(s1);
		skillSet.add(s2);
		skillSet.add(s3);
		skillSet.add(s4);
		skillSet.add(s5);
		Batch b1 = new Batch(15, "Salesforce", new Date(1515733200000L), new Date(1520053200000L), 3, 6, 5, skillSet, 1, 1);
		b1.setEndDate(new Date(1525147200000L));
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);
		ResponseEntity<Batch> reTest = batchController.update(b1);
		assertTrue(reTest.getBody().getEndDate().equals(new Date(1525147200000L)) && reTest.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void updateTestBadRequest() {
		SkillIdHolder s1 = new SkillIdHolder(1);
		SkillIdHolder s2 = new SkillIdHolder(2);
		SkillIdHolder s3 = new SkillIdHolder(3);
		SkillIdHolder s4 = new SkillIdHolder(4);
		SkillIdHolder s5 = new SkillIdHolder(5);
		HashSet<SkillIdHolder> skillSet = new HashSet<SkillIdHolder>();
		skillSet.add(s1);
		skillSet.add(s2);
		skillSet.add(s3);
		skillSet.add(s4);
		skillSet.add(s5);
		Batch b1 = new Batch(15, "Salesforce", new Date(1515733200000L), new Date(1520053200000L), 3, 6, 5, skillSet, 1, 1);
		b1.setEndDate(new Date(1525147200000L));
		ResponseEntity<Batch> reTest = batchController.update(b1);
		assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void deleteTest() {
		Mockito.doNothing().when(batchRepository).deleteById(9);
		ResponseEntity<Batch> reTest = batchController.delete(9);
		assertTrue(reTest.getStatusCode() == HttpStatus.OK);
	}

}
