package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // web.xml -> DispatcherServlet(xml) / ContextLoaderListener(xml) 자동으로 컴포넌트 스캔되어 빈에 등록됨
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
