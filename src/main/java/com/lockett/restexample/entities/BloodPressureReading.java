package com.lockett.restexample.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BloodPressureReading {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "systolic_reading")
  private int stystolicReading;

  @Column(name = "diastolic_reading")
  private int diastolicReading;

  @Column(name = "comments", nullable = true)
  private String comments;

  @Column(name = "created_at", nullable = false)
  private Timestamp createdAt;

  @Column(name = "updated_at", nullable = false)
  private Timestamp updatedAt;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setStystolicReading(int stystolicReading) {
    this.stystolicReading = stystolicReading;
  }

  public int getStystolicReading() {
    return stystolicReading;
  }

  public void setDiastolicReading(int diastolicReading) {
    this.diastolicReading = diastolicReading;
  }

  public int getDiastolicReading() {
    return diastolicReading;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public String getComments() {
    return comments;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

}
