package com.lockett.restexample.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lockett.restexample.entities.BloodPressureReading;
import com.lockett.restexample.models.BodyDto;
import com.lockett.restexample.repositories.BloodPressureReadingRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/readings")
public class BloodPressureReadingController {

  @Autowired
  BloodPressureReadingRepository bloodPressureReadingRepository;

  @GetMapping("/")
  @Transactional
  public ResponseEntity<BodyDto<List<BloodPressureReading>>> getAllReadings() {
    BodyDto<List<BloodPressureReading>> body = new BodyDto<List<BloodPressureReading>>();
    body.setData(bloodPressureReadingRepository.findAll());
    return ResponseEntity.ok(body);
  }

  @GetMapping("/{id}")
  @Transactional
  public ResponseEntity<BodyDto<BloodPressureReading>> getUserById(@PathVariable(value = "id") long id) {
    Optional<BloodPressureReading> optionalBloodPressureReading = bloodPressureReadingRepository.findById(id);

    if (optionalBloodPressureReading.isPresent()) {
      BodyDto<BloodPressureReading> body = new BodyDto<BloodPressureReading>();
      body.setData(optionalBloodPressureReading.get());
      return ResponseEntity.ok(body);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
