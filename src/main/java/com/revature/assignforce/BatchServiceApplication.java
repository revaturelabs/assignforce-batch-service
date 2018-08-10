package com.revature.assignforce;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Profile;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@SpringBootApplication
@Profile("default")
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@EnableRabbit
public class BatchServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(BatchServiceApplication.class).run(args);
	}
}
