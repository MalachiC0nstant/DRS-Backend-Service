//package com.example.DRS_Project;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry
//                        .addMapping("/**")
//                        .allowedMethods(CorsConfiguration.ALL)  // Allow all HTTP methods
//                        .allowedHeaders(CorsConfiguration.ALL)  // Allow all headers
//                        .allowedOriginPatterns("http://localhost:5173")  // Replace '*' with the specific URL
//                        .allowCredentials(true);  // Allow credentials (cookies, authorization headers, etc.)
//            }
//        };
//    }
//}