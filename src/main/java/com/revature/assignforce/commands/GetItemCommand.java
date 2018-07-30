package com.revature.assignforce.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.clients.CurriculumClient;
import com.revature.assignforce.clients.LocationClient;
import com.revature.assignforce.clients.TrainerClient;

@Component
public class GetItemCommand {

	@Autowired
	CurriculumClient curriculumClient;
	@Autowired
	private LocationClient locationClient;
	@Autowired
	private TrainerClient trainerClient;
	
	@HystrixCommand(fallbackMethod = "findCurriculumFallback")
	public Batch findCurriculum(Batch batch) {
		if(!curriculumClient.getById(batch.getCurriculum()).hasBody()) throw new RuntimeException();
		return batch;
	}
	
	@HystrixCommand(fallbackMethod = "findLocationFallback")
	public Batch findLocation(Batch batch) {
		locationClient.getById(batch.getLocation());
		return batch;
	}
	
	@HystrixCommand(fallbackMethod = "findTrainerFallback")
	public Batch findTrainer(Batch batch) {
		trainerClient.getById(batch.getTrainer());
		return batch;
	}
	
	public Batch findCurriculumFallback(Batch batch) {
		batch.setCurriculum(null);
		System.out.println("fallback called");
		return batch;
	}
	
	public Batch findLocationFallback(Batch batch) {
		batch.setLocation(null);
		return batch;
	}
	
	public Batch findTrainerFallback(Batch batch) {
		batch.setTrainer(null);
		return batch;
	}
	
	
}
