package com.DigitalTwin.buildingProvider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.DigitalTwin.buildingProvider.dao.mapper")
public class BuildingProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuildingProviderApplication.class, args);
    }
}
