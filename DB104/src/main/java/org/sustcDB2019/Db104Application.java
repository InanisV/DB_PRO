package com.sustcDB2019.DB104;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.sustcDB2019.DB104.dao")
public class Db104Application {

	public static void main(String[] args) {
		SpringApplication.run(Db104Application.class, args);

	}

}
