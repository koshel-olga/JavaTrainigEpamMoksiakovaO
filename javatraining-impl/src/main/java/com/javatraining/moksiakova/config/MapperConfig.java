package com.javatraining.moksiakova.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * MapperConfig
 *
 */
@Configuration
public class MapperConfig {
    @Bean
    public MappingJackson2HttpMessageConverter customJson() {
        return new MappingJackson2HttpMessageConverter(
                new Jackson2ObjectMapperBuilder()
                        .indentOutput(true)
                        .serializationInclusion(JsonInclude.Include.NON_NULL)
                        .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                        .build()
        );
    }
}
