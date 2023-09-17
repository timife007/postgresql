package com.timife.prodatabase.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
