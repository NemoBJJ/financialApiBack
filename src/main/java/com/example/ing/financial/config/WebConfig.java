package com.example.ing.financial.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                    "http://localhost:3003",
                    "https://nemiapiingfinancial.netlify.app",
                    "https://financialapiback.onrender.com",  // ← NOVA URL DO RENDER
                    "https://3.217.55.187:80",
                    "https://api1.neemindev.com",
                    "https://neemindev.com"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}