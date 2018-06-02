package com.cayzlh.jwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description :
 *
 * <p>SpringbootJwtDemoApplication</p>
 *
 * @author Ant丶
 * @date 2018-04-27.
 */
@MapperScan("com.cayzlh.jwt.mapper")
@SpringBootApplication
public class SpringbootJwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJwtDemoApplication.class, args);
	}
}
