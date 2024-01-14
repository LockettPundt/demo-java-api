package com.lockett.restexample.models;

public class HealthCheckResponse {
  private String message;
  private int status;

  public HealthCheckResponse(String message, int status) {
    this.message = message;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public int getStatus() {
    return status;
  }
}
