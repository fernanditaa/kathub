package com.example.kathub.kathub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            // Configuraciones CORS pueden ser añadidas aquí si es necesario

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // solo los endpoints de API
                        .allowedOrigins("http://localhost:5173") // origen específico
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("Content-Type", "Authorization")
                        .allowCredentials(false);
            }
        };
    }
    
}
