package com.revature.assignforce.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	@GetMapping(path = "locAndCur/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Batch>> getByLocationAndCurriculum(@RequestParam("locationId") int locationId,
															@RequestParam("curriculumId") int curriculumId){
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
	@GetMapping(path = "startingBetween/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Batch>> getByStartingDateBetween(@RequestParam("start")LocalDate startDate,
																@RequestParam("end") LocalDate endDate){

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
	@GetMapping(path = "trainerAndStartingBetween/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Batch>> getByTrainerWithStartingDateBetween(@RequestParam("Id")Integer trainerId,
																		   @RequestParam("start") LocalDate startDate,
																		   @RequestParam("end") LocalDate endDate){
		List<Batch> batches = batchService.getAllBatchesByTrainerStartingBetween(trainerId, startDate,endDate);

		if(batches == null || batches.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(batches, HttpStatus.OK);

	}

    /**
     * <p>Method returns all batches for a given curriculum that have a starting date between startDate and endDate</p>
     * @param curriculumId
     * @param startDate
     * @param endDate
     * @return either the batches or a Not_Found HttpStatus Code.
     */
    @GetMapping(path = "curriculumAndStartingBetween/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Batch>> getByCurriculumWithStartingDateBetween(@RequestParam("Id")Integer curriculumId,
                                                                              @RequestParam("start") LocalDate startDate,
                                                                              @RequestParam("end") LocalDate endDate){
        List<Batch> batches = batchService.getAllBatchesByCurriculumStartingBetween(curriculumId, startDate,endDate);

        if(batches == null || batches.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(batches, HttpStatus.OK);

    }

    /**
     * <p>Method returns all batches for a given location that have a starting date between startDate and endDate</p>
     * @param locationId
     * @param startDate
     * @param endDate
     * @return either the batches or a Not_Found HttpStatus code.
     */
    @GetMapping(path = "locationAndStartingBetween/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Batch>> getByLocationWithStartingDateBetween(@RequestParam(name = "Id")Integer locationId,
                                                                            @RequestParam(name = "start") LocalDate startDate,
                                                                            @RequestParam(name = "end") LocalDate endDate){
        List<Batch> batches = batchService.getAllBatchesByLocationStartingBetween(locationId, startDate,endDate);

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
