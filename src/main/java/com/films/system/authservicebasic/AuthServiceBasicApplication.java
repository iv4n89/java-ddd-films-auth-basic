package com.films.system.authservicebasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@SpringBootApplication
public class AuthServiceBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceBasicApplication.class, args);
	}

}
