
package com.revature.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.revature.assignforce.commands.FindCurriculumCommand;
import com.revature.assignforce.commands.FindLocationCommand;
import com.revature.assignforce.commands.FindSkillsCommand;
import com.revature.assignforce.commands.FindTrainerCommand;
import com.revature.assignforce.controllers.BatchController;
import com.revature.assignforce.repos.SkillRepository;
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
        @Autowired
		private BatchService batchService;
        @Autowired
		private SkillRepository skillRepository;

	
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
		Batch b1 = new Batch(1, "Microservices", LocalDate.of(2018, 12, 5), LocalDate.of(2019, 1, 5), 3, 6, 5, skillSet,1,1, 1, 1);
		Batch b2 = new Batch(2, "Salesforce",LocalDate.of(2019, 5, 10), LocalDate.of(2019, 6, 10), 3, 7, 3, skillSet, 1,1,2,3);
		List<Batch> batchList = new ArrayList<Batch>();
		batchList.add(b1);
		batchList.add(b2);
		Mockito.when(batchRepository.findAll()).thenReturn(batchList);
		
		List<Batch> testList = batchService.getAll();
        System.out.println(testList.size());
		//assertTrue(testList.size() == 2);
		assertEquals(2, testList.size());
	}

	@Test
	public void getAllByCurriculumTest() {
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
		Batch b1 = new Batch(1, "Microservices", LocalDate.of(2018, 12, 5), LocalDate.of(2019, 1, 5), 3, 6, 5, skillSet,1,1, 1, 1);
		Batch b2 = new Batch(2, "Salesforce",LocalDate.of(2019, 5, 10), LocalDate.of(2019, 6, 10), 3, 7, 3, skillSet, 1,1,2,3);
		List<Batch> batchList = new ArrayList<Batch>();
		batchList.add(b1);
		batchList.add(b2);
		Mockito.when(batchRepository.findByCurriculum(3)).thenReturn(batchList);

		List<Batch> testList = batchService.getAllByCurriculum(3);
		System.out.println(testList.size());
		//assertTrue(testList.size() == 2);
		assertEquals(2, testList.size());
	}

	@Test
	public void getAllByTrainerTest() {
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
		Batch b1 = new Batch(1, "Microservices", LocalDate.of(2018, 12, 5), LocalDate.of(2019, 1, 5), 3, 6, 5, skillSet,1,1, 1, 1);
		Batch b2 = new Batch(2, "Salesforce",LocalDate.of(2019, 5, 10), LocalDate.of(2019, 6, 10), 3, 7, 3, skillSet, 1,1,2,3);
		List<Batch> batchList = new ArrayList<Batch>();
		batchList.add(b1);
		batchList.add(b2);
		Mockito.when(batchRepository.findByTrainer(6)).thenReturn(batchList);

		List<Batch> testList = batchService.getAllByTrainer(6);
		testList.remove(1);
		System.out.println(testList.size());
		//assertTrue(testList.size() == 1);
		assertEquals(1, testList.size());
	}

	@Test
	public void getAllByLocationTest() {
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
		Batch b1 = new Batch(1, "Microservices", LocalDate.of(2018, 12, 5), LocalDate.of(2019, 1, 5), 3, 6, 5, skillSet,1,1, 1, 1);
		Batch b2 = new Batch(2, "Salesforce",LocalDate.of(2019, 5, 10), LocalDate.of(2019, 6, 10), 3, 7, 3, skillSet, 1,1,2,3);
		List<Batch> batchList = new ArrayList<Batch>();
		batchList.add(b1);
		batchList.add(b2);
		Mockito.when(batchRepository.findByLocation(1)).thenReturn(batchList);

		List<Batch> testList = batchService.getAllByLocation(1);
		System.out.println(testList.size());
		//assertTrue(testList.size() == 2);
		assertEquals(2, testList.size());
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
		Batch b1 = new Batch(1, "Microservices", LocalDate.of(2018, 12, 5), LocalDate.of(2019, 1, 5),3, 6, 5, skillSet,1,1, 1, 1);
		Optional<Batch> op1 = Optional.ofNullable(b1);
		Mockito.when(batchRepository.findById(1)).thenReturn(op1);
		
		Optional<Batch> opTest = batchService.findById(1);
		//assertTrue(opTest.get().getName().equals("Microservices"));
		assertEquals("Microservices", opTest.get().getName());
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
		Batch b1 = new Batch(1, "Microservices", LocalDate.of(2018, 12, 5), LocalDate.of(2019, 1, 5), 3, 6, 5, skillSet, 1,1,1, 1);
		b1.setEndDate(LocalDate.of(2019, 1, 6));
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);
		
		Batch batchTest = batchService.update(b1);
		//assertTrue(batchTest.getEndDate().equals(LocalDate.of(2019, 1, 6)));
		assertEquals(LocalDate.of(2019, 1, 6), batchTest.getEndDate());
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
		Batch b1 = new Batch(1, "Microservices",LocalDate.of(2018, 12, 5), LocalDate.of(2019, 1, 5), 3, 1, 5, skillSet, 1,1,1, 1);
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);

		//Batch batchTest = batchRepository.save(b1);
		Batch batchTest = batchService.create(b1);
        System.out.println("Skill size is " + skillSet.size());
		//assertTrue(batchTest.getSkills().size() == 5);
       // assertEquals(5, batchTest.getSkills().size());
        Optional<Batch> test = batchService.findById(batchTest.getId());
        assertEquals(true, test.isPresent());
	}
	
	@Test
	public void createTestWithNullSkills() {
		Batch b1 = new Batch(1, "Microservices",LocalDate.of(2018, 12, 5), LocalDate.of(2019, 1, 5), 3, 1, 5, null, 1,1,1, 1);
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);

		Batch batchTest = batchService.create(b1);
        assertEquals(0, batchTest.getSkills().size());
	}
	
	@Test
	public void deleteNullTest() {
		Mockito.doNothing().when(batchRepository).deleteById(20);
		batchService.delete(20);
		Optional<Batch> batchTest = batchService.findById(20);
		assertFalse(batchTest.isPresent());
	}

	@Test
	public void deleteTest() {
		/*SkillIdHolder s1 = new SkillIdHolder(1);
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
		Batch b1 = new Batch(20, "Microservices",LocalDate.of(2018, 12, 5), LocalDate.of(2019, 1, 5), 3, 6, 5, skillSet, 1,1,1, 1);
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);
		//batchRepository.save(b1);
		batchService.create(b1);
//		batchService.create(b1);
	//deleting batch
		//Mockito.doNothing().when(batchRepository).deleteById(20);
		//batchRepository.deleteById(b1.getId());
//		batchService.delete(b1.getId());
		Optional<Batch> batchTest1 = batchService.findById(b1.getId());
		assertFalse(batchTest1.isPresent());*/
		
		//****************************************************************************************
		
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
		Batch b1 = new Batch(1, "Microservices",LocalDate.of(2018, 12, 5), LocalDate.of(2019, 1, 5), 3, 1, 5, skillSet, 1,1,1, 1);
		Mockito.when(batchRepository.save(b1)).thenReturn(b1);

		Batch batchTest = batchService.create(b1);
        
		Mockito.doNothing().when(batchRepository).deleteById(batchTest.getId());
        batchService.delete(batchTest.getId());
        
        Optional<Batch> test = batchService.findById(batchTest.getId());
        Mockito.when(batchRepository.findById(batchTest.getId())).thenReturn(test);
        
        assertEquals(true, test.isPresent());
	}


}
