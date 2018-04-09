package org.cdz.web;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("org.cdz.web")
public class WebApplication {
	private static Logger logger = Logger.getLogger(WebApplication.class);
	/**
	 * Main Start
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
		logger.info("============= SpringBoot Start Success =============");
	}

}
