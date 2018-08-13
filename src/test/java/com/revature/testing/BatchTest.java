
package com.revature.testing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import org.junit.*;


import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
public class BatchTest {

	

	@Configuration
	static class BatchTestContextConfiguration {
		
		
	@Bean
	public Batch Batch() {
		return new Batch();
		}
	}
	
	
	@Test
	public void batchTest1() {
		Batch b1 = new Batch();
		assertNotNull(b1);
	}
	
	@Test
	public void batchTest2() {
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
		Batch b1 = new Batch(1, "Microservices", LocalDate.of(2018, 12, 5), LocalDate.of(2019, 1, 5), 3, 6, 5, skillSet,1, 1, 1, 1);
		assertTrue(b1.getId() == 1);
	}
	
	@Test
	public void getSetIdTest() {
		Batch b1 = new Batch();
		b1.setId(13);
		assertTrue(b1.getId() == 13);
	}
	
	@Test
	public void getSetNameTest() {
		Batch b1 = new Batch();
		b1.setName("BatchOne");
		assertTrue(b1.getName().equals("BatchOne"));
	}
	
	@Test
	public void getSetStartDateTest() {
		Batch b1 = new Batch();
		b1.setStartDate(LocalDate.of(2018, 10, 5));
		assertTrue(b1.getStartDate().isEqual(LocalDate.of(2018, 10, 5)));
	}
	
	@Test
	public void getSetEndDateTest() {
		Batch b1 = new Batch();
		b1.setEndDate(LocalDate.of(2018, 11, 10));
		assertTrue(b1.getEndDate().isEqual(LocalDate.of(2018, 11, 10)));
	}
	
	@Test
	public void getSetCurriculumTest() {
		Batch b1 = new Batch();
		b1.setCurriculum(new Integer(55));
		assertTrue(b1.getCurriculum() == 55);
	}
	
	@Test
	public void getSetTrainerTest() {
		Batch b1 = new Batch();
		b1.setTrainer(new Integer(23));
		assertTrue(b1.getTrainer() == 23);
	}
	
	@Test
	public void getSetCoTrainerTest() {
		Batch b1 = new Batch();
		b1.setCotrainer(new Integer(38));;
		assertTrue(b1.getCotrainer() == 38);
	}
	
	@Test
	public void getSetSkillsTest() {
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
		Batch b1 = new Batch();
		b1.setSkills(skillSet);
		assertTrue(b1.getSkills().size() == 5);
	}
	
	@Test
	public void getSetLocationTest() {
		Batch b1 = new Batch();
		b1.setLocation(new Integer(9));
		assertTrue(b1.getLocation() == 9);
	}

	@Test
	public void getSetBuildingTest() {
		Batch b1 = new Batch();
		b1.setBuilding(new Integer(1));
		assertTrue(b1.getBuilding() == 1);
	}

	@Test
	public void getSetRoomTest() {
		Batch b1 = new Batch();
		b1.setRoom(new Integer(1));
		assertTrue(b1.getRoom() == 1);
	}

	@Test
	public void getSetClassSizeTest() {
		Batch b1 = new Batch();
		b1.setClassSize(new Integer(31));
		assertTrue(b1.getClassSize() == 31);
	}
	
	

}
