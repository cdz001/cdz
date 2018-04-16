package org.cdz.web.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XXXFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		System.out.println(httpRequest.getRequestURI());
		 Enumeration headerNames = httpRequest.getHeaderNames();
//	    while (headerNames.hasMoreElements()) {
//	        String key = (String) headerNames.nextElement();
//	        String value = httpRequest.getHeader(key);
//	        System.out.println(key+" "+value);
//	    }
	    System.out.println("**************************************");
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
//		httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
//		httpResponse.setHeader("Access-Control-Max-Age", "3600");
//		httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    chain.doFilter(request,response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println(arg0.getInitParameter("paramName"));
	}

}
