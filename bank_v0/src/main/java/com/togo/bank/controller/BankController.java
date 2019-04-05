package com.togo.bank.controller;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.togo.bank.service.BankService;
import com.togo.common.pojo.ResultBuilder;
import com.togo.common.pojo.ResultBuilder.Result;

@RestController
@RequestMapping(value = "/bank")
public class BankController {

	@Autowired
	private BankService bankService;

	private ReentrantLock lock = new ReentrantLock();

	@GetMapping(value = "/test")
	public Result test() {

		return ResultBuilder.build();
	}

	@GetMapping(value = "/save/{userId}/{money}")
	public Result saveMoney(@PathVariable int userId, @PathVariable BigDecimal money) {

		Result result = null;
		result = bankService.saveMoney(userId, money);

		return result;
	}

	@GetMapping(value = "/get/{userId}/{money}")
	public Result getMoney(@PathVariable int userId, @PathVariable BigDecimal money) {

		Result result = null;
		result = bankService.getMoney(userId, money);

		return result;
	}
}
