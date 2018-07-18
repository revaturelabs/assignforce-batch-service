package com.revature.assignforce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.service.BatchService;

@CrossOrigin
@RestController
public class BatchController {

	@Autowired
	BatchService batchService;

	// findAll
	@RequestMapping(method = RequestMethod.GET)
	public List<Batch> getAll() {
		return batchService.getAll();
	}

	// findOne
	@RequestMapping(value = "{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> getById(@PathVariable int id) {
		Optional<Batch> b = batchService.findById(id);
		if (!b.isPresent())
			return new ResponseEntity<Batch>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Batch>(b.get(), HttpStatus.OK);
	}

	// create
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> add(@RequestBody Batch a) {
		a = batchService.create(a);
		if (a == null)
			return new ResponseEntity<Batch>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Batch>(a, HttpStatus.CREATED);
	}

	// update
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> update(@PathVariable int id, @RequestBody Batch a) {
		a = batchService.update(a);
		if (a == null)
			return new ResponseEntity<Batch>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Batch>(a, HttpStatus.CREATED);
	}

	// delete
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> delete(@PathVariable int id) {
		batchService.delete(id);
		return new ResponseEntity<Batch>(HttpStatus.CREATED);
	}

}
