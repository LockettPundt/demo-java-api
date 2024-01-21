package com.lockett.restexample.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lockett.restexample.entities.User;
import com.lockett.restexample.models.BodyDto;

public interface UserServiceInterface {

  ResponseEntity<BodyDto<List<User>>> getAllUsers();

  ResponseEntity<BodyDto<User>> getUserById(long id);

  ResponseEntity<BodyDto<Long>> deleteUser(long id);

  ResponseEntity<BodyDto<User>> addUser(User userRequest);
}
