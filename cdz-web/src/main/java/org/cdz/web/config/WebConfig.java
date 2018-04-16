package org.cdz.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
	 @Bean  
	    public FilterRegistrationBean filterDemo4Registration() {  
	        FilterRegistrationBean registration = new FilterRegistrationBean();  
	        registration.setFilter(new XXXFilter());  
	        registration.addUrlPatterns(new String[]{"/*"});  
	        registration.addInitParameter("paramName", "testParamName");  
	        return registration;  
	    }  
}
