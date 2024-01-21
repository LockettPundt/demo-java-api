package com.lockett.restexample.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lockett.restexample.controllers.BloodPressureReadingController;
import com.lockett.restexample.entities.BloodPressureReading;
import com.lockett.restexample.models.BodyDto;
import com.lockett.restexample.repositories.BloodPressureReadingRepository;
import com.lockett.restexample.service.BloodPressureReadingServiceInterface;

@Service
public class BloodPressureReadingService implements BloodPressureReadingServiceInterface {

  private static final Logger logger = LoggerFactory.getLogger(BloodPressureReadingController.class);

  @Autowired
  BloodPressureReadingRepository bloodPressureReadingRepository;

  public ResponseEntity<BodyDto<List<BloodPressureReading>>> getAllReadings() {
    logger.info("Fetching all readings...");

    BodyDto<List<BloodPressureReading>> body = new BodyDto<List<BloodPressureReading>>();
    body.setData(bloodPressureReadingRepository.findAll());

    return ResponseEntity.ok(body);
  }

  public ResponseEntity<BodyDto<BloodPressureReading>> getBloodPressureReadingById(long id) {
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

  public ResponseEntity<BodyDto<BloodPressureReading>> addBloodPressureReading(
      BloodPressureReading bloodPressureReadingRequest) {
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

  public ResponseEntity<BodyDto<Long>> deleteBloodPressureReading(long id) {
    try {
      Optional<BloodPressureReading> bloodPressureReading = bloodPressureReadingRepository.findById(id);
      if (bloodPressureReading.isPresent()) {
        BodyDto<Long> body = new BodyDto<Long>();
        body.setData(id);
        bloodPressureReadingRepository.deleteById(id);

        return ResponseEntity.ok(body);
      } else {
        logger.error("No blood pressure reading found ...");

        return ResponseEntity.noContent().build();
      }
    } catch (Exception e) {
      logger.error("Error deleting blood pressure reading...", e);

      return ResponseEntity.internalServerError().build();
    }
  }
}
