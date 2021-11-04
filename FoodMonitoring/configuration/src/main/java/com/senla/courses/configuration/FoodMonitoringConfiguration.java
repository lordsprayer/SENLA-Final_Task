package com.senla.courses.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({DataSourceConfig.class, KafkaTopicConfig.class, KafkaProducerConfig.class})
@PropertySource({"classpath:jdbc.properties", "classpath:log4j2.properties" })
@ComponentScan(basePackages = "com.senla.courses")
public class FoodMonitoringConfiguration {

}
