package com.ciheul.soc.simulator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("com.ciheul.soc.*")
@PropertySource(ignoreResourceNotFound = true, value = {
		"classpath:simulator.properties", "file:${appConfiguration}" })
public class SimulatorConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer placeHolder = new PropertySourcesPlaceholderConfigurer();
		return placeHolder;
	}
}
