package com.lzz.api.module;


import java.io.Serializable;
import lombok.Data;

/**
 * @author jeremy.li
 * @version V1.0
 * @date 2021/5/15
 * @description
 */
@Data
public class Response<T> implements Serializable {

	private static final long serialVersionUID = 2179489861660458060L;

	private static final int RESPONSE_SUCCESS_CODE = 0;
	private static final int RESPONSE_ERROR_CODE = 999;




	public Response() {
		this(RESPONSE_SUCCESS_CODE, "success");
	}

	public Response(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Response(Integer code, String message, T object) {
		this.code = code;
		this.message = message;
		this.data = object;
	}

	private Integer code;
	private String message;
	private T data;

	public Response(T data) {
		this(RESPONSE_SUCCESS_CODE, "", data);

	}

	public static Response success(Object data) {
		Response response = new Response();
		response.setCode(RESPONSE_SUCCESS_CODE);
		response.setData(data);
		return response;
	}

	public static Response error(String message) {
		Response response = new Response();
		response.setCode(RESPONSE_ERROR_CODE);
		response.setMessage(message);
		return response;
	}
}
