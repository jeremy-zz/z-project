package com.lzz.exception;

import com.alibaba.fastjson.JSON;
import com.lzz.api.module.Response;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author jeremy.li
 * @version V1.0
 * @date 2021/5/24
 * @description 洋钱罐异常拦截
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler({Exception.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Response handlerYqgException(Exception exception) {
		log.error("服务器内部错误，Exception[{}]", exception);
		Response error = Response.error(exception.getMessage());
		return error;
	}

}
