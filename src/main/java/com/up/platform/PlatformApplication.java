package com.up.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
//@MapperScan("com.up.platform.mapper")
@SpringBootApplication
public class PlatformApplication {

    public static void main(String[] args) {SpringApplication.run(PlatformApplication.class, args); }
}
