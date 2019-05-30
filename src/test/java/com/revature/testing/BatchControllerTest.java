


package com.revature.testing;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.sql.Date;
import java.time.LocalDate;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.commands.FindCurriculumCommand;
import com.revature.assignforce.commands.FindLocationCommand;
import com.revature.assignforce.commands.FindSkillsCommand;
import com.revature.assignforce.commands.FindTrainerCommand;
import com.revature.assignforce.controllers.BatchController;
import com.revature.assignforce.repos.BatchRepository;
import com.revature.assignforce.repos.SkillRepository;
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

		@Bean
		public SkillRepository skillRepository() {
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
		
		@Bean
		public FindSkillsCommand findSkillsCommand() {
			return new FindSkillsCommand();
		}
	}

	@Autowired
	private BatchRepository batchRepository;
	@Autowired
	private BatchController batchController;
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
		Batch b1 = new Batch(1, "Microservices",  LocalDate.of(2020, 1, 1), LocalDate.of(2020,2,1), 3, 6, 6, skillSet,
				1, 1, 1, 1);
		Batch b2 = new Batch(1, "Salesforce",  LocalDate.of(2020, 2, 1), LocalDate.of(2020,3,1), 3, 6, 6, skillSet,
				1, 1, 1, 1);
		Batch b3 = new Batch(1, "Database",  LocalDate.of(2020, 4, 1), LocalDate.of(2020,5,1), 3, 6, 6, skillSet,
				1, 1, 1, 1);
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
		Batch b1 = new Batch(3, "Microservices",  LocalDate.of(2020, 1, 1), LocalDate.of(2020,2,1), 3, 6, 6, skillSet,
				1, 1, 1, 1);
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

	
	// updated by Eric and Adam.
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
		Batch b1 = new Batch(5, "Microservices",  LocalDate.of(2020, 1, 1), LocalDate.of(2020,2,1), 3, 6, 6, skillSet,
				1, 1, 1, 1);
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);
//		mockTrainerServer.expect(requestTo("http://localhost:8765/trainer-service/" + b1.getTrainer()))
//		  .andRespond(withSuccess());
//		mockCurriculumServer.expect(requestTo("http://localhost:8765/curriculum-service/" + b1.getCurriculum()))
//		  .andRespond(withSuccess());
//		mockLocationServer.expect(requestTo("http://localhost:8765/location-service/" + b1.getLocation()))
//		  .andRespond(withSuccess());
//		b1.getSkills().forEach((skillIdHolder) ->
//		mockSkillsServer.expect(requestTo("http://localhost:8765/skill-service/" + skillIdHolder.getId()))
//			  .andRespond(withSuccess()));
		ResponseEntity<Batch> reTest = batchController.add(b1);
//		mockTrainerServer.verify();
//		mockLocationServer.verify();
//		mockCurriculumServer.verify();
//		mockSkillsServer.verify();
		assertTrue(reTest.getBody().getId() == 5 && reTest.getStatusCode() == HttpStatus.CREATED);
	}

	
	
	// updated by Eric and Adam.

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
		Batch b1 = new Batch(1, "Microservices",  LocalDate.of(2020, 1, 1), LocalDate.of(2020,2,1), 3, 6, 6, skillSet,
				1, 1, 1, 1);
//		mockTrainerServer.expect(requestTo("http://localhost:8765/trainer-service/" + b1.getTrainer()))
//		  .andRespond(withSuccess());
//		mockCurriculumServer.expect(requestTo("http://localhost:8765/curriculum-service/" + b1.getCurriculum()))
//		  .andRespond(withSuccess());
//		mockLocationServer.expect(requestTo("http://localhost:8765/location-service/" + b1.getLocation()))
//		  .andRespond(withSuccess());
//		b1.getSkills().forEach((skillIdHolder) ->
//		mockSkillsServer.expect(requestTo("http://localhost:8765/skill-service/" + skillIdHolder.getId()))
//			  .andRespond(withSuccess()));
		ResponseEntity<Batch> reTest = batchController.add(b1);
//		mockTrainerServer.verify();
//		mockLocationServer.verify();
//		mockCurriculumServer.verify();
//		mockSkillsServer.verify();
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
		Batch b1 = new Batch(1, "Microservices",  LocalDate.of(2020, 1, 1), LocalDate.of(2020,2,1), 3, 6, 6, skillSet,
				1, 1, 1, 1);
		
	
		b1.setEndDate(LocalDate.of(2020, 2, 2));
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);
		ResponseEntity<Batch> reTest = batchController.update(b1);
		assertTrue(reTest.getBody().getEndDate().equals(LocalDate.of(2020, 2, 2))
				&& reTest.getStatusCode() == HttpStatus.OK);
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
		Batch b1 = new Batch(1, "Microservices",  LocalDate.of(2020, 1, 1), LocalDate.of(2020,2,1), 3, 6, 6, skillSet,
				1, 1, 1, 1);
		b1.setEndDate(LocalDate.of(2020, 2, 2));
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

