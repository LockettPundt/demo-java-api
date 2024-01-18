package com.lockett.restexample.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lockett.restexample.entities.BloodPressureReading;
import com.lockett.restexample.models.BodyDto;
import com.lockett.restexample.repositories.BloodPressureReadingRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/readings")
public class BloodPressureReadingController {

  private static final Logger logger = LoggerFactory.getLogger(BloodPressureReadingController.class);

  @Autowired
  BloodPressureReadingRepository bloodPressureReadingRepository;

  @GetMapping("/")
  public ResponseEntity<BodyDto<List<BloodPressureReading>>> getAllReadings() {
    logger.info("Fetching all readings...");

    BodyDto<List<BloodPressureReading>> body = new BodyDto<List<BloodPressureReading>>();
    body.setData(bloodPressureReadingRepository.findAll());
    return ResponseEntity.ok(body);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BodyDto<BloodPressureReading>> getUserById(@PathVariable(value = "id") long id) {
    Optional<BloodPressureReading> optionalBloodPressureReading = bloodPressureReadingRepository.findById(id);

    if (optionalBloodPressureReading.isPresent()) {
      logger.info("Blood pressure reading found with id...", id);

      BodyDto<BloodPressureReading> body = new BodyDto<BloodPressureReading>();
      body.setData(optionalBloodPressureReading.get());
      return ResponseEntity.ok(body);
    } else {
      logger.error("No blood pressure reading found with id...", id);
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/add-reading")
  @Transactional
  public ResponseEntity<BodyDto<BloodPressureReading>> addBloodPressureReading(
      @Validated @RequestBody @NonNull BloodPressureReading bloodPressureReadingRequest) {
    try {
      logger.info("Creating new blood pressure reading...");

      Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
      bloodPressureReadingRequest.setCreatedAt(timeStamp);
      bloodPressureReadingRequest.setUpdatedAt(timeStamp);
      BloodPressureReading newReading = bloodPressureReadingRepository.save(bloodPressureReadingRequest);
      BodyDto<BloodPressureReading> body = new BodyDto<>();
      body.setData(newReading);
      return ResponseEntity.ok(body);
    } catch (Exception e) {
      logger.error("Error creating new blood pressure reading...", e);

      return ResponseEntity.internalServerError().build();
    }
  }
}
