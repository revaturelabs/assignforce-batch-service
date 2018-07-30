package com.revature.testing;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.clients.CurriculumClient;
import com.revature.assignforce.clients.LocationClient;
import com.revature.assignforce.clients.TrainerClient;
import com.revature.assignforce.commands.GetItemCommand;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GetItemCommandTest {

	static class Curriculum{
		Integer id;
		
		public Curriculum(int id) {
			this.id = id;
		}
	}
	
	@Configuration
	static class BatchServiceTestContextConfiguration {
		@Bean
		public GetItemCommand getItemCommand() {
			return new GetItemCommand();
		}
		
		@Bean
		public CurriculumClient curriculumClient() {
			class CurriculumClientTest implements CurriculumClient {
	
				@Override
				public ResponseEntity<Object> getById(int id) {
					if(id < 0) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} else {
						return new ResponseEntity<>(new Curriculum(id), HttpStatus.OK);
					}
				}
				
			}
			return new CurriculumClientTest();
			
			}
	
		@Bean
		public LocationClient locationClient() {
			class LocationClientTest implements LocationClient {
	
				@Override
				public ResponseEntity<Object> getById(int id) {
					if(id < 0) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} else {
						return new ResponseEntity<>(new Object(), HttpStatus.OK);
					}
				}
				
			}
			return new LocationClientTest();
			
			}
		@Bean
		public TrainerClient trainerClient() {
			class TrainerClientTest implements TrainerClient {
	
				@Override
				public ResponseEntity<Object> getById(int id) {
					if(id < 0) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} else {
						return new ResponseEntity<>(new Object(), HttpStatus.OK);
					}
				}
				
			}
			return new TrainerClientTest();
			
			}
		
		}

		
	
	
	@Autowired
	private GetItemCommand getItemCommand;
	
	
	@Test
	public void curriculumFound() {
		Batch batch = new Batch();
		batch.setCurriculum(1);
		batch = getItemCommand.findCurriculum(batch);
		assertTrue(batch.getCurriculum() == 1);
	}
	
	@Test
	public void curriculumNotFound() {
		Batch batch = new Batch();
		batch.setCurriculum(-1);
		batch = getItemCommand.findCurriculumFallback(batch);
		assertNull(batch.getCurriculum());
	}
	
}
