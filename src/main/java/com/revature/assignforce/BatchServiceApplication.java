package com.revature.assignforce;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class BatchServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(BatchServiceApplication.class).run(args);
	}
}
