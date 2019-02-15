package com.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class App {
	public static void main(String[] args){
		Logger log= LoggerFactory.getLogger(App.class);
			SpringApplication.run(App.class,args);
			log.info("boot");
	}
}
