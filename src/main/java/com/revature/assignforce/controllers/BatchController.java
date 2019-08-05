package com.revature.assignforce.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.service.BatchService;

//@CrossOrigin
@RestController
public class BatchController {

	@Autowired
	BatchService batchService;

	// findAll
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Batch> getAll() {
		return batchService.getAll();
	}

	// findOne
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> getById(@PathVariable int id) {
		Optional<Batch> b = batchService.findById(id);
		if (!b.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(b.get(), HttpStatus.OK);
	}

	// find all by location and curriculum

	/**
	 * <p>Used for getting all the batches given a particular location and Curriculum</p>
	 * @param locationId The location Id int
	 * @param curriculumId The curriculum Id int
	 * @return either the found list of batches and an HttpStatus.OK, or if the list is null or empty, HttpStatus.Not_Found
	 */
	@GetMapping(path = "locAndCur/{locationId}/{curriculumId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Batch>> getByLocationAndCurriculum(@PathVariable("locationId") int locationId,
															@PathVariable("curriculumId") int curriculumId){
		List<Batch> locAndCurr = batchService.getAllByLocationAndCurriculum(locationId,curriculumId);
		if(locAndCurr == null || locAndCurr.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(locAndCurr, HttpStatus.OK);
	}

	/**
	 * <p>Method returns all batches with a starting date between params</p>
	 * @param startDate Starting date of the search
	 * @param endDate the last possible date a batch could start and get included in the results
	 * @return either the batches or a Not_Found HttpStatus
	 */
	@GetMapping(path = "starting/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Batch>> getByStartingDateBetween(@PathVariable("startDate")LocalDate startDate,
																@PathVariable("endDate") LocalDate endDate){

		List<Batch> startingBetween = batchService.getAllBatchesStartingBetween(startDate,endDate);

		if(startingBetween == null || startingBetween.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(startingBetween, HttpStatus.OK);

	}

	/**
	 * <p>Method returns all batches for a trainer with a starting date between the start and end dates</p>
	 * @param trainerId
	 * @param startDate
	 * @param endDate
	 * @return either the batches or a Not_Found HttpStatus Code.
	 */
	@GetMapping(path = "trainerAndStarting/{trainerId}/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Batch>> getByTrainerWithStartingDateBetween(@PathVariable("trainerId")Integer trainerId,
																		   @PathVariable("startDate") LocalDate startDate,
																		   @PathVariable("endDate") LocalDate endDate){
		List<Batch> batches = batchService.getAllBatchesByTrainerStartingBetween(trainerId, startDate,endDate);

		if(batches == null || batches.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(batches, HttpStatus.OK);

	}

	// create
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> add(@RequestBody Batch a) {
		a = batchService.create(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	// update
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> update(@RequestBody Batch a) {
		a = batchService.update(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.OK);
	}

	// delete
	@DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> delete(@PathVariable int id) {
		batchService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
