package com.revature.testing;

import static org.junit.Assert.assertEquals;

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

import com.revature.assignforce.commands.FindCurriculumCommand;
import com.revature.assignforce.commands.FindLocationCommand;
import com.revature.assignforce.commands.FindSkillsCommand;
import com.revature.assignforce.commands.FindTrainerCommand;
import com.revature.assignforce.controllers.SprintController;
import com.revature.assignforce.repos.SkillRepository;

/**
 * SprintController needs to be implemented before it can be tested. As it stands,
 * SprintController is not properly implemented so no testing can be done.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SprintControllerTest {
	
	@Configuration
	static class SprintControllerTestContextConfiguration {
		@Bean
		public SprintController sprintController() {
			return new SprintController();
		}
	}
	
	@Autowired
	private SprintController sprintController;
	
	@Test
	public void testGetProjectSprints() {
	}
	
	@Test
	public void testGetSprintById() {
	}
	
	@Test
	public void testGetSprintColumns() {
	}
}
