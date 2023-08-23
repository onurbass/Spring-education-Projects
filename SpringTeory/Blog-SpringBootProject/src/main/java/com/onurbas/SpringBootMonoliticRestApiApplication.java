package com.onurbas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootMonoliticRestApiApplication {

	public static void main(String[] args) {
	ApplicationContext applicationContext=	SpringApplication.run(SpringBootMonoliticRestApiApplication.class,args);

	}
}
