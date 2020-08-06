package com.ty.pethospital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.ty.pethospital.Mapper")
@EnableSwagger2
public class PethospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PethospitalApplication.class, args);
    }
}
