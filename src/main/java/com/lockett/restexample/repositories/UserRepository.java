package com.lockett.restexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lockett.restexample.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
