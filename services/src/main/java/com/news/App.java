package com.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



@SpringBootApplication
public class App extends SpringBootServletInitializer {
	
		@Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(App.class);
	    }
	public static void main(String[] args){
		Logger log= LoggerFactory.getLogger(App.class);
			SpringApplication.run(App.class,args);
			log.info("boot");
	}
}
