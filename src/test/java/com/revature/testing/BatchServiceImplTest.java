package com.revature.testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.commands.FindCurriculumCommand;
import com.revature.assignforce.commands.FindLocationCommand;
import com.revature.assignforce.commands.FindSkillsCommand;
import com.revature.assignforce.commands.FindTrainerCommand;
import com.revature.assignforce.repos.BatchRepository;
import com.revature.assignforce.repos.SkillRepository;
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

		@Bean
		public SkillRepository SkillRepository() {
			return Mockito.mock(SkillRepository.class);
		}
		
		@Bean
		public FindTrainerCommand findTrainerCommand() {
			return new FindTrainerCommand();
		}
		
		@Bean
		public FindLocationCommand findLocationCommand() {
			return new FindLocationCommand();
		}
		
		@Bean
		public FindCurriculumCommand findCurriculumCommand() {
			return new FindCurriculumCommand();
		}
	}

	@Autowired
	private BatchService batchService;
	@Autowired
	private BatchRepository batchRepository;
	@Autowired
	private SkillRepository skillRepository;
	@Autowired
	private FindTrainerCommand findTrainerCommand;
	@Autowired
	private FindLocationCommand findLocationCommand;
	@Autowired
	private FindCurriculumCommand findCurriculumCommand;
	@Autowired
	private FindSkillsCommand findSkillsCommand;
	
	private MockRestServiceServer mockTrainerServer;
	private MockRestServiceServer mockLocationServer;
	private MockRestServiceServer mockCurriculumServer;
	private MockRestServiceServer mockSkillsServer;
	
	@Before
	public void setup() {
		mockTrainerServer = MockRestServiceServer.bindTo(findTrainerCommand.getRestTemplate()).build();
		mockLocationServer = MockRestServiceServer.bindTo(findLocationCommand.getRestTemplate()).build();
		mockCurriculumServer = MockRestServiceServer.bindTo(findCurriculumCommand.getRestTemplate()).build();
		mockSkillsServer = MockRestServiceServer.bindTo(findSkillsCommand.getRestTemplate()).build();
	}

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
		Batch b1 = new Batch(1, "Microservices", new Date(1515733200000L), new Date(1520053200000L), 3, 6, 5, skillSet,
				1, 1, 1, 1);
		Batch b2 = new Batch(2, "Salesforce", new Date(1517634000000L), new Date(1522209600000L), 3, 7, 3, skillSet, 2,
				3, 1, 1);
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
		Batch b1 = new Batch(1, "Microservices", new Date(1515733200000L), new Date(1520053200000L), 3, 6, 5, skillSet,
				1, 1, 1, 1);
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
		Batch b1 = new Batch(1, "Microservices", new Date(1515733200000L), new Date(1520053200000L), 3, 6, 5, skillSet,
				1, 1, 1, 1);
		b1.setEndDate(new Date(1559707200000L));
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);

		Batch batchTest = batchService.update(b1);
		assertTrue(batchTest.getEndDate().equals(new Date(1559707200000L)));
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
		Batch b1 = new Batch(1, "Microservices", new Date(1515733200000L), new Date(1520053200000L), 3, 6, 5, skillSet,
				1, 1, 1, 1);
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);
		mockTrainerServer.expect(requestTo("http://localhost:8765/trainer-service/" + b1.getTrainer()))
		  .andRespond(withSuccess());
		mockCurriculumServer.expect(requestTo("http://localhost:8765/curriculum-service/" + b1.getCurriculum()))
		  .andRespond(withSuccess());
		mockLocationServer.expect(requestTo("http://localhost:8765/location-service/" + b1.getLocation()))
		  .andRespond(withSuccess());
		Batch batchTest = batchService.create(b1);
		mockTrainerServer.verify();
		mockLocationServer.verify();
		mockCurriculumServer.verify();
		assertTrue(batchTest.getSkills().size() == 5);
	}
	
	@Test
	public void createTest2() {
		Batch b1 = new Batch();
		b1.setBuilding(19);
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);
		mockTrainerServer.expect(requestTo("http://localhost:8765/trainer-service/" + b1.getTrainer()))
		  .andRespond(withSuccess());
		mockCurriculumServer.expect(requestTo("http://localhost:8765/curriculum-service/" + b1.getCurriculum()))
		  .andRespond(withSuccess());
		mockLocationServer.expect(requestTo("http://localhost:8765/location-service/" + b1.getLocation()))
		  .andRespond(withSuccess());
		Batch bTest = batchService.create(b1);
		mockTrainerServer.verify();
		mockLocationServer.verify();
		mockCurriculumServer.verify();
		assertTrue(bTest.getBuilding() == 19);
	}

	@Test
	public void deleteTest() {
		Mockito.doNothing().when(batchRepository).deleteById(20);
		batchService.delete(20);
		Optional<Batch> batchTest = batchService.findById(20);
		assertFalse(batchTest.isPresent());
	}
	
	@Test
	public void deleteTest2() {
		Batch b1 = new Batch();
		b1.setId(26);
		Optional<Batch> op1 = Optional.ofNullable(b1);
		Mockito.when(batchRepository.findById(26)).thenReturn(op1);
		Mockito.doNothing().when(batchRepository).deleteById(26);
		batchService.delete(26);
		Mockito.when(batchRepository.findById(26)).thenReturn(Optional.ofNullable(null));
		Optional<Batch> opTest = batchService.findById(26);
		assertFalse(opTest.isPresent());
	}

}
