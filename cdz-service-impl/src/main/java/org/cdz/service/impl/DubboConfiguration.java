package org.cdz.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboConfiguration {
 
 
    @Bean
    // @DependsOn("dubboConfiguration")
    public AnnotationBean dubboAnnotationBean() {
        AnnotationBean annotationBean = new AnnotationBean();
        // annotationBean.setApplicationContext(applicationContext);
        // annotationBean.setPackage(dubboProperties.getAnnotationPackage());
        // annotationBean.setPackage("com.roc.pay");
        return annotationBean;
    }
 
}