package com.togo.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.togo.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

	Account findByUserId(int userId);
}
