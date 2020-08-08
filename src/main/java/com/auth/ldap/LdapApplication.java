package com.auth.ldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class LdapApplication {

	public static void main(String[] args) {
		SpringApplication.run(LdapApplication.class, args);
	}

}
