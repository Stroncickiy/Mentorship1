package com.epam.spring.app;


import com.epam.spring.configuration.ApplicationConfig;
import com.epam.spring.configuration.DispatcherServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationConfig.class)
                .child(DispatcherServletInitializer.class)
                .run(args);
    }


}
