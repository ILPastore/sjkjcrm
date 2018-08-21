package com.sjkjcrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SjkjcrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SjkjcrmApplication.class, args);
    }
}
