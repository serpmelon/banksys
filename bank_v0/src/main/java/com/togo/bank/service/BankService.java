package com.togo.bank.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.togo.bank.dao.AccountRecordRepository;
import com.togo.bank.dao.AccountRepository;
import com.togo.bank.dao.UserRepository;
import com.togo.bank.entity.Account;
import com.togo.bank.entity.User;
import com.togo.common.pojo.ResultBuilder;
import com.togo.common.pojo.ResultBuilder.Result;

@Service
@Transactional
public class BankService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private AccountRecordRepository aRecordRepo;

	public Result saveMoney(int userId, BigDecimal money) {

		Result result = null;
		try {
			if (isValidUser(userId)) {

				Account account = accountRepo.findByUserId(userId);
				BigDecimal nowMoney = account.getMoney();
				account.setMoney(account.getMoney().add(money));
				accountRepo.save(account);
				result = ResultBuilder.build(true, nowMoney + " -> " + account.getMoney());
			} else
				result = ResultBuilder.build(false, "user is invalid");
		} catch (Exception e) {

			e.printStackTrace();
			result = ResultBuilder.buildByException(e);
		}

		return result;
	}

	private boolean isValidUser(int userId) {

		User user = userRepo.getOne(userId);

		return user != null;
	}
}
