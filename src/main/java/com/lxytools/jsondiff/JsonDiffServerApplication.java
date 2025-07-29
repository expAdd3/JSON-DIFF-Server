package com.lxytools.jsondiff;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lxytools.jsondiff.mapper")
public class JsonDiffServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonDiffServerApplication.class, args);
    }

}
