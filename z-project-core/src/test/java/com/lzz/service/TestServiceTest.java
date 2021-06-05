package com.lzz.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;


public class TestServiceTest extends BaseTest {

	@Resource
	TestService testService;

	@Value("${redis.password}")
	private String redisPassword;

	@Test
	public void getUserName() {
		System.out.println("redisPassword:" + redisPassword);
		testService.getUserName("project");
	}

	@Autowired
	RedisTemplate redisTemplate;

	@Test
	public void redis() {
		redisTemplate.opsForValue().set("name", "jeremy123");
		String name = (String) redisTemplate.opsForValue().get("name");
		System.out.println("name:" + name);
	}
}
