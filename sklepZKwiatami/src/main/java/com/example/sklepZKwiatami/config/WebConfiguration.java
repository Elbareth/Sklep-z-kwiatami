package com.example.sklepZKwiatami.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//TODO sprawdz dokumentacje localhost:8080/swagger-ui jesli ie dziala to strona 154
public class WebConfiguration implements WebMvcConfigurer {
}
