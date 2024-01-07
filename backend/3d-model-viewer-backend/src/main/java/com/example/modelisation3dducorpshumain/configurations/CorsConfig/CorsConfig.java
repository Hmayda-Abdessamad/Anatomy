package com.example.modelisation3dducorpshumain.configurations.CorsConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // This allows CORS for all endpoints.
                        .allowedOrigins("http://localhost:4200") // Replace with your frontend's origin.
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods.
                        .allowedHeaders("*") // Allowed request headers
                        ; // Include credentials in the CORS request (if needed).
            }
        };
    }
}

