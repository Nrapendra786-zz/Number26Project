package com.number26proj.application;

import com.number26proj.utils.ComponentUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main class of the project
 * Created by NrapendraKumar on 05-03-2016.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan({ComponentUtil.CONTROLLERS, ComponentUtil.SERVICES, ComponentUtil.RESOURCES, ComponentUtil.MODELS})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
