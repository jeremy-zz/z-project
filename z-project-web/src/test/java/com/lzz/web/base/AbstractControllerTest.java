/**
 * @Title: ConfigConsts.java
 * @Copyright: © 2017 我来贷
 * @Company: 深圳卫盈智信科技有限公司
 */
package com.lzz.web.base;

import com.alibaba.fastjson.JSON;
import com.lzz.Application;
import org.junit.After;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Copyright 2017 Welab, Inc. All rights reserved. WELAB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * 控制层测试抽象类
 *
 * @author TODO 请填写作者信息，如：jason.zhuo
 * @date TODO 请填写日期，如：2018-01-02 13:56:00
 * @version v1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public abstract class AbstractControllerTest extends AbstractBaseTest {

	protected static Logger logger = LoggerFactory.getLogger(AbstractControllerTest.class);

	/**
	 * MVC mock
	 */
	@Resource
	protected MockMvc mockMvc;

	@After
	public void tearDown() throws Exception {
	}


	/**
	 * Mock the Common request
	 *
	 * @param requestBuilder 构造请求对象
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @param params 请求参数
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private MockHttpServletRequestBuilder commonJsonMockBuilder(MockHttpServletRequestBuilder requestBuilder,
		Object request, Object params, Object headers) throws Exception {
		requestBuilder.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8");
		// Body请求体，服务于"@RequestBody"
		if (Objects.nonNull(request) && !"".equals(request)) {
			requestBuilder.content(JSON.toJSONString(request));
		}

		// Parameters请求体，服务于"@RequestParam"
		if (Objects.nonNull(params) && !"".equals(params)) {
			Object javaObject = JSON.toJSON(params);
			if (javaObject instanceof Map) {
				Map<Object, Object> map = (Map<Object, Object>) javaObject;
				for (Map.Entry<Object, Object> entry : map.entrySet()) {
					requestBuilder.param((String) entry.getKey(),
						Objects.isNull(entry.getValue()) ? null : entry.getValue().toString());
				}
			}
		}

		// Header请求体，服务于"@RequestHeader"
		if (Objects.nonNull(headers) && !"".equals(headers)) {
			Object javaObject = JSON.toJSON(headers);
			if (javaObject instanceof Map) {
				Map<Object, Object> map = (Map<Object, Object>) javaObject;
				for (Map.Entry<Object, Object> entry : map.entrySet()) {
					requestBuilder.header((String) entry.getKey(),
						Objects.isNull(entry.getValue()) ? null : entry.getValue().toString());
				}
			}
		}

		return requestBuilder;
	}

	/**
	 * Mock the GET request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param params 请求参数
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions getJsonMock(String url, Object request, Object params, Object headers) throws Exception {
		return this.jsonRequestMock(getJsonMockBuilder(url, request, params, headers));
	}

	/**
	 * Mock the GET request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @throws Exception
	 */
	protected ResultActions getJsonRequestMock(String url, Object request) throws Exception {
		return this.jsonRequestMock(getJsonMockBuilder(url, request, null, null));
	}

	/**
	 * Mock the GET request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions getJsonRequestMock(String url, Object request, Object headers) throws Exception {
		return this.jsonRequestMock(getJsonMockBuilder(url, request, null, headers));
	}

	/**
	 * Mock the GET request
	 *
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions getJsonParamMock(String url, Object params, Object headers) throws Exception {
		return this.jsonRequestMock(getJsonMockBuilder(url, null, params, headers));
	}

	/**
	 * Mock the GET request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @param params 请求参数
	 * @throws Exception
	 */
	private MockHttpServletRequestBuilder getJsonMockBuilder(String url, Object request, Object params, Object headers)
		throws Exception {
		return commonJsonMockBuilder(get(url).session(new MockHttpSession()), request, params, headers);
	}

	/**
	 * Mock the GET request
	 *
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param headers 请求头参数
	 * @param urlVars
	 * @PathVariable 修饰的数据
	 * @throws Exception
	 */
	protected ResultActions getJsonParamMock(String url, Object params, Object headers, Object... urlVars)
		throws Exception {
		return this.jsonRequestMock(getJsonMockBuilder(url, null, params, headers, urlVars));
	}

	/**
	 * Mock the GET request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @param params 请求参数
	 * @throws Exception
	 */
	private MockHttpServletRequestBuilder getJsonMockBuilder(String url, Object request, Object params, Object headers,
		Object... urlVars) throws Exception {
		return commonJsonMockBuilder(get(url, urlVars).session(new MockHttpSession()), request, params, headers);
	}

	/**
	 * Mock the POST request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param params 请求参数
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions postJsonMock(String url, Object request, Object params, Object headers) throws Exception {
		return this.jsonRequestMock(postJsonMockBuilder(url, request, params, headers));
	}

	/**
	 * Mock the POST request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @throws Exception
	 */
	protected ResultActions postJsonRequestMock(String url, Object request) throws Exception {
		return this.jsonRequestMock(postJsonMockBuilder(url, request, null, null));
	}

	/**
	 * Mock the POST request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions postJsonRequestMock(String url, Object request, Object headers) throws Exception {
		return this.jsonRequestMock(postJsonMockBuilder(url, request, null, headers));
	}

	/**
	 * Mock the POST request
	 *
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions postJsonParamMock(String url, Object params, Object headers) throws Exception {
		return this.jsonRequestMock(postJsonMockBuilder(url, null, params, headers));
	}

	/**
	 * Mock the POST request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @param params 请求参数
	 * @throws Exception
	 */
	private MockHttpServletRequestBuilder postJsonMockBuilder(String url, Object request, Object params, Object headers)
		throws Exception {
		return commonJsonMockBuilder(post(url).session(new MockHttpSession()), request, params, headers);
	}

	/**
	 * Mock the PUT request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param params 请求参数
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions putJsonMock(String url, Object request, Object params, Object headers) throws Exception {
		return this.jsonRequestMock(putJsonMockBuilder(url, request, params, headers));
	}

	/**
	 * Mock the PUT request
	 *
	 * @param url 请求地址
	 * @param request 请求头参数
	 * @throws Exception
	 */
	protected ResultActions putJsonRequestMock(String url, Object request) throws Exception {
		return this.jsonRequestMock(putJsonMockBuilder(url, request, null, null));
	}

	/**
	 * Mock the PUT request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions putJsonRequestMock(String url, Object request, Object headers) throws Exception {
		return this.jsonRequestMock(putJsonMockBuilder(url, request, null, headers));
	}

	/**
	 * Mock the PUT request
	 *
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions putJsonParamMock(String url, Object params, Object headers) throws Exception {
		return this.jsonRequestMock(putJsonMockBuilder(url, null, params, headers));
	}

	/**
	 * Mock the PUT request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @param params 请求参数
	 * @throws Exception
	 */
	private MockHttpServletRequestBuilder putJsonMockBuilder(String url, Object request, Object params, Object headers)
		throws Exception {
		return commonJsonMockBuilder(put(url).session(new MockHttpSession()), request, params, headers);
	}

	/**
	 * Mock the PATCH request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param params 请求参数
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions patchJsonMock(String url, Object request, Object params, Object headers) throws Exception {
		return this.jsonRequestMock(patchJsonMockBuilder(url, request, params, headers));
	}

	/**
	 * Mock the PATCH request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @throws Exception
	 */
	protected ResultActions patchJsonRequestMock(String url, Object request) throws Exception {
		return this.jsonRequestMock(patchJsonMockBuilder(url, request, null, null));
	}

	/**
	 * Mock the PATCH request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions patchJsonRequestMock(String url, Object request, Object headers) throws Exception {
		return this.jsonRequestMock(patchJsonMockBuilder(url, request, null, headers));
	}

	/**
	 * Mock the PATCH request
	 *
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions patchJsonParamMock(String url, Object params, Object headers) throws Exception {
		return this.jsonRequestMock(patchJsonMockBuilder(url, null, params, headers));
	}

	/**
	 * Mock the PATCH request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @param params 请求参数
	 * @throws Exception
	 */
	private MockHttpServletRequestBuilder patchJsonMockBuilder(String url, Object request, Object params,
		Object headers) throws Exception {
		return commonJsonMockBuilder(patch(url).session(new MockHttpSession()), request, params, headers);
	}

	/**
	 * Mock the DELETE request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param params 请求参数
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions deleteJsonMock(String url, Object request, Object params, Object headers) throws Exception {
		return this.jsonRequestMock(deleteJsonMockBuilder(url, request, params, headers));
	}

	/**
	 * Mock the DELETE request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @throws Exception
	 */
	protected ResultActions deleteJsonRequestMock(String url, Object request) throws Exception {
		return this.jsonRequestMock(deleteJsonMockBuilder(url, request, null, null));
	}

	/**
	 * Mock the DELETE request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions deleteJsonRequestMock(String url, Object request, Object headers) throws Exception {
		return this.jsonRequestMock(deleteJsonMockBuilder(url, request, null, headers));
	}

	/**
	 * Mock the DELETE request
	 *
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions deleteJsonParamMock(String url, Object params, Object headers) throws Exception {
		return this.jsonRequestMock(deleteJsonMockBuilder(url, null, params, headers));
	}

	/**
	 * Mock the DELETE request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @param params 请求参数
	 * @throws Exception
	 */
	private MockHttpServletRequestBuilder deleteJsonMockBuilder(String url, Object request, Object params,
		Object headers) throws Exception {
		return commonJsonMockBuilder(delete(url).session(new MockHttpSession()), request, params, headers);
	}

	/**
	 * Print the request result
	 *
	 * @param requestBuilder 构造的请求
	 * @throws Exception
	 */
	private ResultActions jsonRequestMock(MockHttpServletRequestBuilder requestBuilder) throws Exception {
		// 打印整个请求与响应细节
		return this.mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print());
	}

	/**
	 * Mock the Common fileUpload request
	 *
	 * @param requestBuilder 构造请求对象
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @param params 请求参数
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private MockMultipartHttpServletRequestBuilder commonMockMultipartBuilder(
		MockMultipartHttpServletRequestBuilder requestBuilder, Object request, Object params, Object headers)
		throws Exception {
		requestBuilder.contentType(MediaType.MULTIPART_FORM_DATA).characterEncoding("UTF-8");
		// Body请求体，服务于"@RequestBody"
		if (Objects.nonNull(request) && !"".equals(request)) {
			requestBuilder.content(JSON.toJSONString(request));
		}

		// Parameters请求体，服务于"@RequestParam"
		if (Objects.nonNull(params) && !"".equals(params)) {
			Object javaObject = JSON.toJSON(params);
			if (javaObject instanceof Map) {
				Map<Object, Object> map = (Map<Object, Object>) javaObject;
				for (Map.Entry<Object, Object> entry : map.entrySet()) {
					requestBuilder.param((String) entry.getKey(),
						Objects.isNull(entry.getValue()) ? null : entry.getValue().toString());
				}
			}
		}

		// Header请求体，服务于"@RequestHeader"
		if (Objects.nonNull(headers) && !"".equals(headers)) {
			Object javaObject = JSON.toJSON(headers);
			if (javaObject instanceof Map) {
				Map<Object, Object> map = (Map<Object, Object>) javaObject;
				for (Map.Entry<Object, Object> entry : map.entrySet()) {
					requestBuilder.header((String) entry.getKey(),
						Objects.isNull(entry.getValue()) ? null : entry.getValue().toString());
				}
			}
		}

		return requestBuilder;
	}

	/**
	 * Mock the fileUpload request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param params 请求参数
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions fileUploadMock(String url, MockMultipartFile file, Object request, Object params,
		Object headers) throws Exception {
		return this.fileUpladMockResultActions(fileUploadMockBuilder(url, file, request, params, headers));
	}

	/**
	 * Mock the fileUpload request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @throws Exception
	 */
	protected ResultActions fileUploadMockRequest(String url, MockMultipartFile file, Object request) throws Exception {
		return this.fileUpladMockResultActions(fileUploadMockBuilder(url, file, request, null, null));
	}

	/**
	 * Mock the fileUpload request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions fileUploadMockRequest(String url, MockMultipartFile file, Object request, Object headers)
		throws Exception {
		return this.fileUpladMockResultActions(fileUploadMockBuilder(url, file, request, null, headers));
	}

	/**
	 * Mock the fileUpload request
	 *
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions fileUploadMockParam(String url, MockMultipartFile file, Object params, Object headers)
		throws Exception {
		return this.fileUpladMockResultActions(fileUploadMockBuilder(url, file, null, params, headers));
	}

	/**
	 * Mock the fileUpload header
	 *
	 * @param url 请求地址
	 * @param file 请求参数
	 * @param headers 请求头参数
	 * @throws Exception
	 */
	protected ResultActions fileUploadMockHeader(String url, MockMultipartFile file, Object headers) throws Exception {
		return this.fileUpladMockResultActions(fileUploadMockBuilder(url, file, null, null, headers));
	}

	/**
	 * Mock the fileUpload request
	 *
	 * @param url 请求地址
	 * @param request 请求对象
	 * @param headers 请求头参数
	 * @param params 请求参数
	 * @throws Exception
	 */
	private MockMultipartHttpServletRequestBuilder fileUploadMockBuilder(String url, MockMultipartFile file,
		Object request, Object params, Object headers) throws Exception {
		return commonMockMultipartBuilder(fileUpload(url).file(file), request, params, headers);
	}

	/**
	 * Print the request result
	 *
	 * @param requestBuilder 构造的请求
	 * @throws Exception
	 */
	private ResultActions fileUpladMockResultActions(MockMultipartHttpServletRequestBuilder requestBuilder)
		throws Exception {
		return this.mockMvc.perform(requestBuilder);
	}


}
