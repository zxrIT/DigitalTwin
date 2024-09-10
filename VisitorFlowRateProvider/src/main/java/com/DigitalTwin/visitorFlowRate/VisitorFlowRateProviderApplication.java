package com.DigitalTwin.visitorFlowRate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class VisitorFlowRateProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(VisitorFlowRateProviderApplication.class, args);
    }
}