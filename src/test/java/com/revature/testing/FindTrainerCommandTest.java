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
import com.revature.assignforce.commands.FindTrainerCommand;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableCircuitBreaker
@EnableAspectJAutoProxy
public class FindTrainerCommandTest {
	
	@Configuration
	static class BatchServiceTestContextConfiguration {
		@Bean
		public FindTrainerCommand findTrainerCommand() {
			
			return new FindTrainerCommand();
		}
		
	}

	@Autowired
	private FindTrainerCommand findTrainerCommand;
	
	private MockRestServiceServer mockServer;
	
	@Before
	public void setup() {
		mockServer = MockRestServiceServer.bindTo(findTrainerCommand.getRestTemplate()).build();
	}
	
	@Test
	public void trainerFound() {
		Batch batch = new Batch();
		batch.setTrainer(1);
		
		mockServer.expect(requestTo("http://localhost:8765/trainer-service/" + batch.getTrainer()))
		  .andRespond(withSuccess());
		batch = findTrainerCommand.findTrainer(batch);
		
		mockServer.verify();
        assertEquals(batch.getTrainer(), Integer.valueOf(1));
	}
	
	@Test
	public void trainerNotFound() {
		Batch batch = new Batch();
		batch.setTrainer(1);
		
		mockServer.expect(requestTo("http://localhost:8765/trainer-service/" + batch.getTrainer()))
		  .andRespond(withBadRequest());
		batch = findTrainerCommand.findTrainer(batch);
		
		mockServer.verify();
        assertNull(batch.getTrainer());
	}
	
	@Test
	public void fallbackMethodTest() {
		Batch b = new Batch();
		b.setTrainer(1);
		b = findTrainerCommand.findTrainerFallback(b);
		assertNull(b.getTrainer());
	}

	
}
