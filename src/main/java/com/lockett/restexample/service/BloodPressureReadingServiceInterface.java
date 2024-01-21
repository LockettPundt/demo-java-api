package com.lockett.restexample.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lockett.restexample.entities.BloodPressureReading;
import com.lockett.restexample.models.BloodPressureRequestDto;
import com.lockett.restexample.models.BodyDto;

public interface BloodPressureReadingServiceInterface {

  ResponseEntity<BodyDto<List<BloodPressureReading>>> getAllReadings();

  ResponseEntity<BodyDto<BloodPressureReading>> getBloodPressureReadingById(long id);

  ResponseEntity<BodyDto<BloodPressureReading>> addBloodPressureReading(
      BloodPressureRequestDto bloodPressureReadingRequest);

  ResponseEntity<BodyDto<Long>> deleteBloodPressureReading(long id);

}
