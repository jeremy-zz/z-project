/**
 *
 */
package com.lzz.web.base;


import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 公共测试代码
 *
 * @author fuhua.huang
 */
public abstract class AbstractBaseTest {

	protected static RestTemplate template = new RestTemplate();
	static Map<String, Object> userLoginMap = null;

}
