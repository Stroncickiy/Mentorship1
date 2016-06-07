package com.epam.orm.app;


import org.springframework.boot.builder.SpringApplicationBuilder;

import com.epam.orm.configuration.DispatcherServletInitializer;

public class Application {

    public static void main(String[] args) {
    	System.setProperty("javax.xml.accessExternalSchema", "all");
        new SpringApplicationBuilder(DispatcherServletInitializer.class)
                .run(args);
    }


}
