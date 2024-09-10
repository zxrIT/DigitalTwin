package com.DigitalTwin.visitorFlowRate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.DigitalTwin.visitorFlowRate.mapper")
public class VisitorFlowRateProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(VisitorFlowRateProviderApplication.class, args);
    }
}