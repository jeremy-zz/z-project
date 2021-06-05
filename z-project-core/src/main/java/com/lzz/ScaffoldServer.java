/**
 * @Title: ConfigConsts.java
 * @Copyright: © 2017 我来贷
 * @Company: 深圳卫盈智信科技有限公司
 */

package com.lzz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author TODO 请修改用户名，例如：dawn.deng
 * @version v1.0
 * @description 项目启动类
 * @date TODO 请修改时间，例如：2017-12-18 17:45:21
 */
@ImportResource({"classpath:/applicationContext.xml"})
public class ScaffoldServer {

	private static Logger LOG = LoggerFactory.getLogger(ScaffoldServer.class);

	public static void main(String[] args) {
		try {
			SpringApplication.run(ScaffoldServer.class, args);
		} catch (Exception e) {
			LOG.error("Start FAIL.", e);
		}
	}
}
