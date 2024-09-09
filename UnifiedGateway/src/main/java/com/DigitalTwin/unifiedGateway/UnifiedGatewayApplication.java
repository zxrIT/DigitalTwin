package com.DigitalTwin.unifiedGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UnifiedGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnifiedGatewayApplication.class, args);
    }
}
