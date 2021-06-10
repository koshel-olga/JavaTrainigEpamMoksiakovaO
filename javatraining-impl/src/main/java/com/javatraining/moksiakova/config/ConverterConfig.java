package com.javatraining.moksiakova.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

/**
 * ConverterConfig
 *
 */
@Configuration
public class ConverterConfig {

    @Bean
    public ConversionService conversionService(){
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        Set<Converter<?, ?>> convSet = new HashSet<>();
        //convSet.add(new StringToRequestConverter());
        factory.setConverters(convSet);
        factory.afterPropertiesSet();
        return factory.getObject();
    }


}
