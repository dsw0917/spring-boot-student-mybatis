package com.yxt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.yxt.officialVehicl.management.*.dao", "com.yxt.domain.mapper"})
@ComponentScan(basePackages = {"com.yxt.officialVehicl", "com.yxt"})
public class SpringBootStudentMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStudentMybatisApplication.class, args);
	}
}
