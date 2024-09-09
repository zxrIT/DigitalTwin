package com.DigitalTwin.smokeAlarmProvider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.DigitalTwin.smokeAlarmProvider.mapper")
public class SmokeAlarmProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmokeAlarmProviderApplication.class, args);
    }
}
