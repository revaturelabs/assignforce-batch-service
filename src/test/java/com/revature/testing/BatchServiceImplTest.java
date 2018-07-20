package com.revature.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.repos.BatchRepository;
import com.revature.assignforce.service.BatchService;
import com.revature.assignforce.service.BatchServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BatchServiceImplTest {

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
	}
	
	@Autowired
	private BatchService batchService;
	@Autowired
	private BatchRepository batchRepository;
	
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
		Batch b1 = new Batch(1, "Microservices", "01-12-2018", "03-03-2018", 3, 6, 5, skillSet, 1, 1);
		Batch b2 = new Batch(2, "Salesforce", "02-03-2018", "03-28-2018", 3, 7, 3, skillSet, 2,3);
		List<Batch> batchList = new ArrayList<Batch>();
		batchList.add(b1);
		batchList.add(b2);
		Mockito.when(batchRepository.findAll()).thenReturn(batchList);
		
		List<Batch> testList = batchService.getAll();
		assertTrue(testList.size() == 2);
	}
	
	@Test
	public void findByIdTest() {
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
		Batch b1 = new Batch(1, "Microservices", "01-12-2018", "03-03-2018", 3, 6, 5, skillSet, 1, 1);
		Optional<Batch> op1 = Optional.ofNullable(b1);
		Mockito.when(batchRepository.findById(1)).thenReturn(op1);
		
		Optional<Batch> opTest = batchService.findById(1);
		assertTrue(opTest.get().getName().equals("Microservices"));
	}
	
	@Test
	public void updateTest() {
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
		Date startDate = new Date(124L);
		Date endDate = new Date(200L);
		Batch b1 = new Batch(1, "Microservices", "01-12-2018", "03-03-2018", 3, 6, 5, skillSet, 1, 1);
		b1.setEndDate("06-05-2019");
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);
		
		Batch batchTest = batchService.update(b1);
		assertTrue(batchTest.getEndDate().equals("06-05-2019"));
	}
	
	@Test
	public void createTest() {
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
		Batch b1 = new Batch(1, "Microservices", "01-12-2018", "03-03-2018", 3, 6, 5, skillSet, 1, 1);
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);
		
		Batch batchTest = batchService.create(b1);
		assertTrue(batchTest.getSkills().size() == 5);
	}
	
	@Test
	public void deleteTest() {
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
		Batch b1 = new Batch(20, "AWS", "01-12-2018", "03-03-2018", 3, 6, 5, skillSet, 1, 1);
		Mockito.doNothing().when(batchRepository).deleteById(20);
		batchService.delete(20);
		Optional<Batch> batchTest = batchService.findById(20);
		assertFalse(batchTest.isPresent());
		
	}

}
