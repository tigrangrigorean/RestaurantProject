package com.ordering_system.service.converter;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
