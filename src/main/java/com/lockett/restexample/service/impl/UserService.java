package com.lockett.restexample.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.lockett.restexample.controllers.UserController;
import com.lockett.restexample.entities.User;
import com.lockett.restexample.models.BodyDto;
import com.lockett.restexample.models.UserRequestDto;
import com.lockett.restexample.repositories.UserRepository;
import com.lockett.restexample.service.UserServiceInterface;

import jakarta.transaction.Transactional;

public class UserService implements UserServiceInterface {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  UserRepository userRepository;

  public ResponseEntity<BodyDto<List<User>>> getAllUsers() {
    BodyDto<List<User>> body = new BodyDto<List<User>>();
    body.setData(userRepository.findAll());
    return ResponseEntity.ok(body);
  }

  public ResponseEntity<BodyDto<User>> getUserById(long id) {
    Optional<User> optionalUser = userRepository.findById(id);

    if (optionalUser.isPresent()) {
      BodyDto<User> body = new BodyDto<User>();
      body.setData(optionalUser.get());
      return ResponseEntity.ok(body);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Transactional
  public ResponseEntity<BodyDto<User>> addUser(UserRequestDto userRequestDto) {
    try {
      logger.info("Creating new user...");

      User newUser = new User();
      Timestamp timeStamp = new Timestamp(System.currentTimeMillis());

      newUser.setFirstName(userRequestDto.getFirstName());
      newUser.setLastName(userRequestDto.getLastName());
      newUser.setEmail(userRequestDto.getEmail());
      newUser.setCreatedAt(timeStamp);
      newUser.setUpdatedAt(timeStamp);

      User user = userRepository.save(newUser);
      BodyDto<User> body = new BodyDto<>();
      body.setData(user);
      return ResponseEntity.ok(body);
    } catch (Exception e) {
      logger.error("Error creating new user...", e);

      return ResponseEntity.internalServerError().build();
    }
  }

  @Transactional
  public ResponseEntity<BodyDto<Long>> deleteUser(long id) {
    try {
      Optional<User> optionalUser = userRepository.findById(id);
      if (optionalUser.isPresent()) {
        BodyDto<Long> body = new BodyDto<Long>();
        body.setData(id);
        userRepository.deleteById(id);

        return ResponseEntity.ok(body);
      } else {
        logger.error("No user found ...");

        return ResponseEntity.noContent().build();
      }
    } catch (Exception e) {
      logger.error("Error deleting user...", e);

      return ResponseEntity.internalServerError().build();
    }
  }
}
