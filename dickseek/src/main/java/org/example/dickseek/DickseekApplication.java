package org.example.dickseek;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication

public class DickseekApplication {
    public static void main(String[] args) {
        SpringApplication.run(DickseekApplication.class, args);
    }
}