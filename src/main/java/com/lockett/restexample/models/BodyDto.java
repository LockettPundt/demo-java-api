package com.lockett.restexample.models;

public class BodyDto<T> {
  private T data;

  public void setData(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }
}
