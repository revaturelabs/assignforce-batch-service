package com.revature.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.commands.FindCurriculumCommand;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableCircuitBreaker
@EnableAspectJAutoProxy
public class FindCurriculumCommandTest {

	@Configuration
	static class BatchServiceTestContextConfiguration {
		@Bean
		public FindCurriculumCommand findCurriculumCommand() {
			
			return new FindCurriculumCommand();
		}
		
	}

	@Autowired
	private FindCurriculumCommand findCurriculumCommand;
	
	private MockRestServiceServer mockServer;
	
	@Before
	public void setup() {
		mockServer = MockRestServiceServer.bindTo(findCurriculumCommand.getRestTemplate()).build();
	}
	
	@Test
	public void curriculumFound() {
		Batch batch = new Batch();
		batch.setCurriculum(1);
		
		mockServer.expect(requestTo("http://localhost:8765/curriculum-service/" + batch.getCurriculum()))
		  .andRespond(withSuccess());
		batch = findCurriculumCommand.findCurriculum(batch);
		
		mockServer.verify();
        assertEquals(batch.getCurriculum(), Integer.valueOf(1));
	}
	
	@Test
	public void curriculumNotFound() {
		Batch batch = new Batch();
		batch.setCurriculum(1);
		
		mockServer.expect(requestTo("http://localhost:8765/curriculum-service/" + batch.getCurriculum()))
		  .andRespond(withBadRequest());
		batch = findCurriculumCommand.findCurriculum(batch);
		
		mockServer.verify();
        assertNull(batch.getCurriculum());
	}
	
	@Test
	public void fallbackMethodTest() {
		Batch b = new Batch();
		b.setCurriculum(1);
		b = findCurriculumCommand.findCurriculumFallback(b);
		assertNull(b.getCurriculum());
	}


}
