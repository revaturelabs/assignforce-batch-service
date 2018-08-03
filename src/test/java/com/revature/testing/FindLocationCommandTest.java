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
import com.revature.assignforce.commands.FindLocationCommand;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableCircuitBreaker
@EnableAspectJAutoProxy
public class FindLocationCommandTest {

	@Configuration
	static class BatchServiceTestContextConfiguration {
		@Bean
		public FindLocationCommand findLocationCommand() {
			
			return new FindLocationCommand();
		}
		
	}

	@Autowired
	private FindLocationCommand findLocationCommand;
	
	private MockRestServiceServer mockServer;
	
	@Before
	public void setup() {
		mockServer = MockRestServiceServer.bindTo(findLocationCommand.getRestTemplate()).build();
	}
	
	@Test
	public void locationFound() {
		Batch batch = new Batch();
		batch.setLocation(1);
		
		mockServer.expect(requestTo("http://localhost:8765/location-service/" + batch.getLocation()))
		  .andRespond(withSuccess());
		batch = findLocationCommand.findLocation(batch);
		
		mockServer.verify();
        assertEquals(batch.getLocation(), Integer.valueOf(1));
	}
	
	@Test
	public void locationNotFound() {
		Batch batch = new Batch();
		batch.setLocation(1);
		
		mockServer.expect(requestTo("http://localhost:8765/location-service/" + batch.getLocation()))
		  .andRespond(withBadRequest());
		batch = findLocationCommand.findLocation(batch);
		
		mockServer.verify();
        assertNull(batch.getLocation());
	}
	
	@Test
	public void fallbackMethodTest() {
		Batch b = new Batch();
		b.setLocation(1);
		b = findLocationCommand.findLocationFallback(b);
		assertNull(b.getLocation());
	}
}
