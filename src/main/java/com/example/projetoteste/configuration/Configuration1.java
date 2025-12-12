package com.example.projetoteste.configuration;

import com.example.projetoteste.service.Service1;
import com.example.projetoteste.service.ServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration1 {

    @Bean
    public Service1 service1() {
        return new ServiceImpl();
    }

}
