package com.inf600c.demo;

import com.inf600c.demo.utils.S3Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		S3Properties.getInstance().setAccessKey(args[0]);
		S3Properties.getInstance().setSecretKey(args[1]);
		SpringApplication.run(DemoApplication.class, args);
	}
}
