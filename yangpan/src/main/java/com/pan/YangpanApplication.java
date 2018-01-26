package com.pan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@MapperScan("com.pan.mapper")//配置mybatis包扫描
//@EnableJms
public class YangpanApplication {

	public static void main(String[] args) {
		SpringApplication.run(YangpanApplication.class, args);
	}
}
