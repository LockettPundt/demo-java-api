package com.lockett.restexample.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lockett.restexample.entities.User;
import com.lockett.restexample.repositories.UserRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/")
  @Transactional
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/{id}")
  @Transactional
  public Optional<User> getUserById(@PathVariable(value = "id") long id) {
    return userRepository.findById(id);
  }
}
