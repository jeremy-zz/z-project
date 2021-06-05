package com.lzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com")
@ImportResource({"classpath:/applicationContext.xml"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket createRestApi() {
		Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
			@Override
			public boolean apply(RequestHandler input) {
				Class<?> declaringClass = input.declaringClass();
				if (declaringClass == BasicErrorController.class)// 排除
				{
					return false;
				}
				if (declaringClass.isAnnotationPresent(RestController.class)) // 被注解的类
				{
					return true;
				}
				if (input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
				{
					return true;
				}
				return false;
			}
		};
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
			.useDefaultResponseMessages(false).select().apis(predicate).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("包含媒体、咨询、搜索引擎关键字、广告等类型接口的服务")// 大标题
			.version("1.0")// 版本
			.build();
	}

}
