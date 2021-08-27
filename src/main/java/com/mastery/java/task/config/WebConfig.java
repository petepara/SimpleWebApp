package com.mastery.java.task.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.mastery.java.task")
@EnableWebMvc
public class WebConfig{

}
