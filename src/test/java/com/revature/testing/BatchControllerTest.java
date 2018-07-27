package com.revature.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
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
		Batch b1 = new Batch(1, "Microservices", LocalDate.of(2018, 9, 1), LocalDate.of(2018, 10, 1), 3, 6, 5, skillSet, 1, 1);
		Batch b2 = new Batch(2, "Salesforce",LocalDate.of(2018, 10, 2), LocalDate.of(2018, 11, 2), 3, 7, 3, skillSet, 2,3);
		Batch b3 = new Batch(4, "Database", LocalDate.of(2018, 11, 3), LocalDate.of(2018, 12, 4), 5, 3, 5, skillSet, 2, 1);
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
		Batch b1 = new Batch(3, "Microservices",LocalDate.of(2018, 12, 5), LocalDate.of(2019, 1, 5), 3, 6, 5, skillSet, 1, 1);
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
		Batch b1 = new Batch(5, "AWS", LocalDate.of(2019, 1, 6), LocalDate.of(2019, 2, 6), 3, 6, 5, skillSet, 1, 1);
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
		Batch b1 = new Batch(15, "Salesforce",  LocalDate.of(2019, 3, 7), LocalDate.of(2019, 4, 7), 3, 6, 5, skillSet, 1, 1);
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
		Batch b1 = new Batch(15, "Salesforce", LocalDate.of(2019, 4, 8), LocalDate.of(2019, 5, 8), 3, 6, 5, skillSet, 1, 1);
		b1.setEndDate(LocalDate.of(2019, 5,9 ));
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);
		ResponseEntity<Batch> reTest = batchController.update(b1);
		assertTrue(reTest.getBody().getEndDate().equals(LocalDate.of(2019, 5,9)) && reTest.getStatusCode() == HttpStatus.OK);
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
		Batch b1 = new Batch(15, "Salesforce", LocalDate.of(2019, 5, 10), LocalDate.of(2019, 6, 10), 3, 6, 5, skillSet, 1, 1);
		b1.setEndDate(LocalDate.of(2019, 6, 11));
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
