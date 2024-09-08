package com.DigitalTwin.buildingProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BuildingProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuildingProviderApplication.class, args);
    }
}
