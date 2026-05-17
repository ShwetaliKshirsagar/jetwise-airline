package com.jetwise_airline.flight_service.config;

import com.hazelcast.config.ClasspathYamlConfig;
import com.hazelcast.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {
    @Bean
    public Config hazelcastConfigmap() {
        return new ClasspathYamlConfig("hazelcast.yaml");
    }
    }
