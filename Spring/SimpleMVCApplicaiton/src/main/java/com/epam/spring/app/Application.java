package com.epam.spring.app;


import com.epam.spring.configuration.ApplicationConfig;
import com.epam.spring.configuration.DispatcherServletInitializer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationConfig.class)
                .child(DispatcherServletInitializer.class)
                .run(args);
    }


}
