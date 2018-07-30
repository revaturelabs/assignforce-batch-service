package com.revature.assignforce.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("location-service/")
public interface LocationClient{
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") int id);
}
