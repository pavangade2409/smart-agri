package com.agri.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FarmerAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(FarmerAgentApplication.class, args);
    }

}