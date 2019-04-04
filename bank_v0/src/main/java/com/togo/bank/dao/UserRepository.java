package com.togo.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.togo.bank.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
