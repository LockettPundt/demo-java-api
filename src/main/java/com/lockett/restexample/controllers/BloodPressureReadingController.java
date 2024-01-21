package com.lockett.restexample.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lockett.restexample.entities.BloodPressureReading;
import com.lockett.restexample.models.BodyDto;
import com.lockett.restexample.service.BloodPressureReadingServiceInterface;

@RestController
@RequestMapping("/readings")
public class BloodPressureReadingController {

  @Autowired
  BloodPressureReadingServiceInterface bloodPressureReadingService;

  @GetMapping("/")
  public ResponseEntity<BodyDto<List<BloodPressureReading>>> getAllReadings() {
    return bloodPressureReadingService.getAllReadings();
  }

  @GetMapping("/{id}")
  public ResponseEntity<BodyDto<BloodPressureReading>> getBloodPressureReadingById(
      @Validated @PathVariable(value = "id") long id) {
    return bloodPressureReadingService.getBloodPressureReadingById(id);
  }

  @PostMapping("/add-reading")
  public ResponseEntity<BodyDto<BloodPressureReading>> addBloodPressureReading(
      @Validated @RequestBody @NonNull BloodPressureReading bloodPressureReadingRequest) {
    return bloodPressureReadingService.addBloodPressureReading(bloodPressureReadingRequest);
  }

  @DeleteMapping("/delete-reading/{id}")
  public ResponseEntity<BodyDto<Long>> deleteBloodPressureReading(@Validated @PathVariable(value = "id") long id) {
    return bloodPressureReadingService.deleteBloodPressureReading(id);
  }

  // TODO PUT endpoint
  // TODO PUT Request DTO
  // TODO Post Request DTO
}
