package com.senla.courses.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{FoodMonitoringConfiguration.class, WebSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        //Parameters:-
        //   location - the directory location where files will be stored
        //   maxFileSize - the maximum size allowed for uploaded files
        //   maxRequestSize - the maximum size allowed for multipart/form-data requests
        //   fileSizeThreshold - the size threshold after which files will be written to disk
        MultipartConfigElement multipartConfig = new MultipartConfigElement("D:/", 1048576,
                10485760, 0);
        registration.setMultipartConfig(multipartConfig);
    }
}
