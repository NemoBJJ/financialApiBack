package com.example.ing.financial.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Aplica CORS a todas as rotas sob /api
                .allowedOrigins(
                    "https://nemiapiingfinancial.netlify.app", // FrontEnd no Netlify
                    "http://localhost:3002", // FrontEnd local
                    "https://3.217.55.187:80", // IP da instância AWS
                    "https://api1.neemindev.com", // Domínio do BackEnd
                    "https://neemindev.com" // Novo domínio do FrontEnd
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Headers permitidos
                .allowCredentials(true); // Permite credenciais (cookies, autenticação)
    }
}