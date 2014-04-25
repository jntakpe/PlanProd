package com.github.jntakpe.pp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDataSourceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Classe mère de l'application
 *
 * @author jntakpe
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = {EmbeddedDataSourceConfiguration.class})
public class PP {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PP.class, args);
    }

}
