package com.revature.assignforce.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("trainer-service/")
public interface TrainerClient{

	@GetMapping(value = "{id}")
	public ResponseEntity<Object> getById(@PathVariable int id);
}
