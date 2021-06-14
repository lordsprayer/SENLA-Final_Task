package com.senla.courses.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({DataSourceConfig.class})
@PropertySource({"classpath:jdbc.properties" })
@ComponentScan(basePackages = "com.senla.courses")
public class FoodMonitoringConfiguration {
}
