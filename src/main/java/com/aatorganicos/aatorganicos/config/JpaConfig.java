package com.aatorganicos.aatorganicos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.aatorganicos.aatorganicos.repository")
public class JpaConfig {
    
}
