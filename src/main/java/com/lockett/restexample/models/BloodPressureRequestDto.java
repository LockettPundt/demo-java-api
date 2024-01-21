package com.lockett.restexample.models;

public class BloodPressureRequestDto {

  private Long userId;
  private int systolicReading;
  private int diastolicReading;
  private String comments;

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setSystolicReading(int systolicReading) {
    this.systolicReading = systolicReading;
  }

  public int getSystolicReading() {
    return systolicReading;
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
}
