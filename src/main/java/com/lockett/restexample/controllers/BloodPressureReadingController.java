package com.lockett.restexample.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    return bloodPressureReadingRepository.findAll();
  }

  @GetMapping("/{id}")
  @Transactional
  public Optional<BloodPressureReading> getUserById(@PathVariable(value = "id") long id) {
    return bloodPressureReadingRepository.findById(id);
  }
}
