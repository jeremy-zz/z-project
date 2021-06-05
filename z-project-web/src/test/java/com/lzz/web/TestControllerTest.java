package com.lzz.web;

import com.lzz.web.base.AbstractControllerTest;
import org.junit.Test;

public class TestControllerTest extends AbstractControllerTest {

	@Test
	public void getUser() {
	}

	@Test
	public void getCity() throws Exception {
		String url = "/test/city/1";
		this.getJsonRequestMock(url, null);
	}
}
