package com.togo.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.togo.bank.entity.AccountRecord;

public interface AccountRecordRepository extends JpaRepository<AccountRecord, String> {

}
