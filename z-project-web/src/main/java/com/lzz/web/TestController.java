package com.lzz.web;

import com.lzz.api.module.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Api("TestController")
@EnableAutoConfiguration
@Controller
@RequestMapping("/test")
public class TestController {

	protected static Logger logger = LoggerFactory.getLogger(TestController.class);

	@ApiOperation("根据id获取用户")
	@ResponseBody
	@RequestMapping("/{id}")
	public Response getUser(@ApiParam(defaultValue = "用户ID") @PathVariable String id) {
		logger.info("用户:" + id);
		return Response.success("用户:" + id);
	}
}