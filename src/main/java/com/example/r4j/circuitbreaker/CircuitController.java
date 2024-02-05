package com.example.r4j.circuitbreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CircuitController {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/hello")
	@CircuitBreaker(name = "order-service", fallbackMethod = "fallback")
	public String hello() {
		return restTemplate.getForObject("http://localhost:8083/three", String.class);
	}

	public String fallback(Throwable t) {
		return "Fallback Message: " + t.getMessage();
	}
}
