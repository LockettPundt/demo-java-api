package com.lockett.restexample.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lockett.restexample.entities.User;
import com.lockett.restexample.models.BodyDto;
import com.lockett.restexample.repositories.UserRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/")
  @Transactional
  public ResponseEntity<BodyDto<List<User>>> getAllUsers() {
    BodyDto<List<User>> body = new BodyDto<List<User>>();
    body.setData(userRepository.findAll());
    return ResponseEntity.ok(body);
  }

  @GetMapping("/{id}")
  @Transactional
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
}
