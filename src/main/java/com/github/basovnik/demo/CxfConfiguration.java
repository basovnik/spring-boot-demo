package com.github.basovnik.demo;

import org.apache.cxf.jaxrs.provider.json.JSONProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfiguration {

    @Bean
    public JSONProvider jsonProvider() {
        return new JSONProvider();
    }
}
