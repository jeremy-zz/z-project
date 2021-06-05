package com.lzz.service.impl;

import com.lzz.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author project.li
 * @date 2018/6/1 17:56
 * @description TODO
 */
@Service
public class TestServiceImpl implements TestService {

	@Override
	public void getUserName(String name) {
		System.out.println("name:" + name);
	}
}
