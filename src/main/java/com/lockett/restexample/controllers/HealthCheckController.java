package com.lockett.restexample.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lockett.restexample.models.BodyDto;
import com.lockett.restexample.models.HealthCheckResponse;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

  @GetMapping("/")
  public ResponseEntity<BodyDto<HealthCheckResponse>> getHealthCheck() {
    BodyDto<HealthCheckResponse> body = new BodyDto<HealthCheckResponse>();
    HealthCheckResponse healthCheckResponse = new HealthCheckResponse("The server is running.", 200);
    body.setData(healthCheckResponse);
    return ResponseEntity.ok(body);
  }
}
