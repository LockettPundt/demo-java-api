package com.lockett.restexample.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lockett.restexample.entities.BloodPressureReading;
import com.lockett.restexample.repositories.BloodPressureReadingRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/readings")
public class BloodPressureReadingController {

  @Autowired
  BloodPressureReadingRepository bloodPressureReadingRepository;

  @GetMapping("/")
  @Transactional
  public List<BloodPressureReading> getAllReadings() {
    // TODO update return object to ResponseEntity with "data" property.
    return bloodPressureReadingRepository.findAll();
  }
}
