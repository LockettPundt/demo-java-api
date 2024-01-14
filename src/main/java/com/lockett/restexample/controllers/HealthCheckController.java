package com.lockett.restexample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lockett.restexample.models.HealthCheckResponse;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

  @GetMapping("/")
  public HealthCheckResponse getHealthCheck() {
    return new HealthCheckResponse("The server is running.", 200);
  }
}
