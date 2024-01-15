package com.lockett.restexample.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lockett.restexample.models.HealthCheckResponse;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

  @GetMapping("/")
  public ResponseEntity<HealthCheckResponse> getHealthCheck() {
    HealthCheckResponse healthCheckResponse = new HealthCheckResponse("The server is running.", 200);
    // TODO update to a general response that has a "data" property
    return ResponseEntity.ok(healthCheckResponse);
  }
}
