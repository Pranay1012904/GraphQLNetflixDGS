package com.dgs.graphQLrev2.prac_1.dataSource;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public Faker faker(){
        return new Faker();
    }
}
