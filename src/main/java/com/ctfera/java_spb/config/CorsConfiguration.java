package com.ctfera.java_spb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Para ativar o uso do Cors deve implementar a interface WebMvcConfigurer
@Configuration  // Definindo annotation de configuração
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**") // Informando que tudo após a raiz deve ser permitido
                .allowedOrigins("http://localhost/") //Informando o host/origem
                .allowedMethods("*");

    }

}
