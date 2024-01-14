package com.lockett.restexample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheck {

  @GetMapping("/")
  public String getHealthCheck() {
    return "HI";
  }
}
