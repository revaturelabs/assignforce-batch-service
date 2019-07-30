package com.revature.testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.commands.FindSkillsCommand;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableCircuitBreaker
@EnableAspectJAutoProxy
public class FindSkillsCommandTest {

	@Configuration
	static class BatchServiceTestContextConfiguration {
		@Bean
		public FindSkillsCommand findSkillsCommand() {
			
			return new FindSkillsCommand();
		}
		
	}

	@Autowired
	private FindSkillsCommand findSkillsCommand;
	
	private MockRestServiceServer mockServer;
	
	@Before
	public void setup() {
		mockServer = MockRestServiceServer.bindTo(findSkillsCommand.getRestTemplate()).build();
	}
	
	@Test
	public void SkillFound() {
		SkillIdHolder skillIdHolder = new SkillIdHolder(1);
		mockServer.expect(requestTo("http://localhost:8765/skill-service/" + skillIdHolder.getId()))
		  .andRespond(withSuccess());
		boolean found = findSkillsCommand.findSkill(skillIdHolder);
		mockServer.verify();
        assertTrue(found);
	}
	
	@Test
	public void skillNotFound() {
		SkillIdHolder skillIdHolder = new SkillIdHolder(1);
		mockServer.expect(requestTo("http://localhost:8765/skill-service/" + skillIdHolder.getId()))
		  .andRespond(withBadRequest());
		boolean found = findSkillsCommand.findSkill(skillIdHolder);
		mockServer.verify();
        assertFalse(found);
	}
	
	@Test
	public void fallbackMethodTest() {
		assertFalse(findSkillsCommand.findSkillFallback(new SkillIdHolder(1)));
	}
	
}
