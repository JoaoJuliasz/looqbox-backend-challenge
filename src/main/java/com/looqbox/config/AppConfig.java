package com.looqbox.config;

import com.looqbox.sorting.imp.ListSort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ListSort listSort() {
        return new ListSort();
    }

}
