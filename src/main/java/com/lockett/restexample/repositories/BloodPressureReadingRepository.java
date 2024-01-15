package com.lockett.restexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lockett.restexample.entities.BloodPressureReading;

public interface BloodPressureReadingRepository extends JpaRepository<BloodPressureReading, Long> {
}
