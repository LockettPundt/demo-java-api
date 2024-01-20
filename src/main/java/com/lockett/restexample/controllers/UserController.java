package com.lockett.restexample.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lockett.restexample.entities.User;
import com.lockett.restexample.models.BodyDto;
import com.lockett.restexample.repositories.UserRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/users")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  UserRepository userRepository;

  @GetMapping("/")
  public ResponseEntity<BodyDto<List<User>>> getAllUsers() {
    BodyDto<List<User>> body = new BodyDto<List<User>>();
    body.setData(userRepository.findAll());
    return ResponseEntity.ok(body);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BodyDto<User>> getUserById(@PathVariable(value = "id") long id) {
    Optional<User> optionalUser = userRepository.findById(id);

    if (optionalUser.isPresent()) {
      BodyDto<User> body = new BodyDto<User>();
      body.setData(optionalUser.get());
      return ResponseEntity.ok(body);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/add-user")
  @Transactional
  public ResponseEntity<BodyDto<User>> addUser(@Validated @RequestBody User userRequest) {
    try {
      logger.info("Creating new user...");

      Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
      userRequest.setCreatedAt(timeStamp);
      userRequest.setUpdatedAt(timeStamp);
      User newUser = userRepository.save(userRequest);
      BodyDto<User> body = new BodyDto<>();
      body.setData(newUser);
      return ResponseEntity.ok(body);
    } catch (Exception e) {
      logger.error("Error creating new user...", e);

      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping("/delete-user/{id}")
  @Transactional
  public ResponseEntity<BodyDto<Long>> deleteUser(@Validated @PathVariable(value = "id") long id) {
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
