package com.epam.spring.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.epam.spring.*")
@PropertySources({
        @PropertySource("classpath:app.properties"),
})
@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy
public class ApplicationConfig {

    private EmbeddedDatabase db;

    @Bean
    public DataSource dataSource() {
        if (db == null) {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            db = builder
                    .setType(EmbeddedDatabaseType.H2)
                    .addScript("classpath:create-db.sql")
                    .build();

        }
        return db;
    }


    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        return new TomcatEmbeddedServletContainerFactory();

    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public Executor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

}
