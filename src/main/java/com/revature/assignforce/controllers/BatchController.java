package com.revature.assignforce.controllers;

import java.sql.SQLException;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.service.BatchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//@CrossOrigin
@RestController
@Api(value = "BatchController")
public class BatchController {

	@Autowired
	BatchService batchService;

	/**
	 * Find All Batches using a get request and return a list of items
	 * 
	 * @return	List of all Batches
	 */
	@ApiOperation(value = "List All Batches from the System ", response = Batch.class, tags = "BatchController")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Batch> getAll() {
		return batchService.getAll();
	}

	// 
	/**
	 * Find Batch by id using get request and return status 200 - OK. 
	 * If no batch found, return status 404 - not found
	 * 
	 * @param id	Batch by Id
	 * @return		ResponseEntity
	 */
	@ApiOperation(value = "Find Batch by Id from the System ", response = ResponseEntity.class, tags = "BatchController")
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> getById(@ApiParam(name="id") @PathVariable int id) {
		Optional<Batch> b = batchService.findById(id);
		if (!b.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(b.get(), HttpStatus.OK);
	}

	/**
	 * 	create batch with unique id, name, start date, end date, curriculum id, trainer id, co-trainer id, 
	 * 		and return status 201 - created.
	 * 
	 * 	If the request doesn't fit the parameters, 
	 * 		it returns status 400 - bad request
	 * 
	 * @param a		Create Batch with unique id, name, start date, end date, curriculum id, trainer id, co-trainer id
	 * @return		ResponseEntity
	 */
	@ApiOperation(value = "Create a Batch to insert into System", response = ResponseEntity.class, tags = "BatchController")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> add(@RequestBody Batch a) {
		a = batchService.create(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	/**
	 * 	Update batches' name, start date, end date, and return status 200 - OK
	 *	If the request doesn't fit the parameters, it returns status 400 - bad request
	 * 
	 * @param a		Update Batch name, start date, end date
	 * @return		ResponseEntity
	 */
	@ApiOperation(value = "Update Batch Information", response = ResponseEntity.class, tags = "BatchController")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> update(@RequestBody Batch a) {
		a = batchService.update(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.OK);
	}
 
	/**
	 * Find a batch by id and delete. Return status 200 - OK
	 * 
	 * @param id	Batch By Id
	 * @return		ResponseEntity
	 */
	@ApiOperation(value = "Delete Batch by Id from the System ", response = ResponseEntity.class, tags = "BatchController")
	@DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> delete(@ApiParam(name="id") @PathVariable int id) {
		batchService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
