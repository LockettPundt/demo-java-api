package com.lockett.restexample.controllers;

import java.util.List;

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
import com.lockett.restexample.service.impl.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserService userService;

  @GetMapping("/")
  public ResponseEntity<BodyDto<List<User>>> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<BodyDto<User>> getUserById(@PathVariable(value = "id") long id) {
    return userService.getUserById(id);
  }

  @PostMapping("/add-user")
  public ResponseEntity<BodyDto<User>> addUser(@Validated @RequestBody User userRequest) {
    return userService.addUser(userRequest);
  }

  @DeleteMapping("/delete-user/{id}")
  public ResponseEntity<BodyDto<Long>> deleteUser(@Validated @PathVariable(value = "id") long id) {
    return userService.deleteUser(id);
  }

  // TODO PUT endpoint
  // TODO PUT Request DTO
  // TODO Post Request DTO
}
