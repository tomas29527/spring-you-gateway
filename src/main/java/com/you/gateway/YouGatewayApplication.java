package com.you.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class YouGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouGatewayApplication.class, args);
    }
}
