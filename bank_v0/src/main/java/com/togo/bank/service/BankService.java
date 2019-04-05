package com.togo.bank.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.togo.bank.dao.AccountRecordRepository;
import com.togo.bank.dao.AccountRepository;
import com.togo.bank.dao.UserRepository;
import com.togo.bank.entity.Account;
import com.togo.bank.entity.AccountRecord;
import com.togo.bank.entity.User;
import com.togo.common.pojo.ResultBuilder;
import com.togo.common.pojo.ResultBuilder.Result;
import com.togo.common.util.UuidUtil;

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
				accountRepo.saveAndFlush(account);
				result = ResultBuilder.build(true, nowMoney + " -> " + account.getMoney());
				AccountRecord ar = new AccountRecord();
				ar.setId(UuidUtil.uuidWithoutLine());
				ar.setCreateTime(new Date());
				ar.setUserId(userId);
				ar.setMoney(money);
				aRecordRepo.save(ar);
			} else
				result = ResultBuilder.build(false, "user is invalid");
		} catch (Exception e) {

			e.printStackTrace();
			result = ResultBuilder.buildByException(e);
		}

		return result;
	}

	public Result getMoney(int userId, BigDecimal money) {
		Result result = null;
		try {
			if (isValidUser(userId)) {

				Account account = accountRepo.findByUserId(userId);
				BigDecimal nowMoney = account.getMoney();
				if (nowMoney.compareTo(money) < 0) {

					result = ResultBuilder.build(false, "do not have enough money");
				} else {
					account.setMoney(nowMoney.subtract(money));
					accountRepo.save(account);
					result = ResultBuilder.build(true, nowMoney + " -> " + account.getMoney());

					AccountRecord ar = new AccountRecord();
					ar.setId(UuidUtil.uuidWithoutLine());
					ar.setCreateTime(new Date());
					ar.setUserId(userId);
					ar.setMoney(money.negate());
					aRecordRepo.save(ar);
				}

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
