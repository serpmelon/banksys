package com.togo.bank;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.togo.common.pojo.ResultBuilder.Result;

@SpringBootTest
@RunWith(SpringRunner.class)
@Rollback
@Transactional
public class BankTestSave {

	@Autowired
	private RestTemplate restTemplate;

//	@Test
//	public void testSave() {
//		this.restTemplate.getForEntity("http://localhost:11001/bank/save/1/100", Result.class, "");
//		// System.out.println(String.format("测试结果为：%s", response.getBody()));
//
//	}
	
	@Test
	public void testSave() {
		this.restTemplate.getForEntity("http://localhost:11001/bank/get/1/100", Result.class, "");
		// System.out.println(String.format("测试结果为：%s", response.getBody()));

	}
}
