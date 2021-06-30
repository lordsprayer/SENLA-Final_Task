package com.senla.courses.configuration;

import org.springframework.context.annotation.*;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
@Import({DataSourceConfig.class})
@PropertySource({"classpath:jdbc.properties", "classpath:log4j2.properties" })
@ComponentScan(basePackages = "com.senla.courses")
public class FoodMonitoringConfiguration {

}
