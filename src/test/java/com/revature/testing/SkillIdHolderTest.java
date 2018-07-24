package com.revature.testing;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.repos.BatchRepository;
import com.revature.assignforce.service.BatchService;
import com.revature.assignforce.service.BatchServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillIdHolderTest {

	@Configuration
	static class SkillIdHolderTestContextConfiguration {
	@Bean
	public SkillIdHolder SkillIdHolder() {
		return new SkillIdHolder();
		}
	}
	
	@Test
	public void skillIdHolderTest1() {
		SkillIdHolder s1 = new SkillIdHolder();
		assertNotNull(s1);
	}
	
	@Test
	public void skillIdHolderTest2() {
		SkillIdHolder s1 = new SkillIdHolder(4);
		assertTrue(s1.getSkillId() == 4);
	}
	
	@Test
	public void getSetSkillIdTest() {
		SkillIdHolder s1 = new SkillIdHolder();
		s1.setSkillID(39);
		assertTrue(s1.getSkillId() == 39);
	}

}
