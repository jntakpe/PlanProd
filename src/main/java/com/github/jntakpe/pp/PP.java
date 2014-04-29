package com.github.jntakpe.pp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Classe mère de l'application
 *
 * @author jntakpe
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class PP {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PP.class, args);
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages/gui-i18n");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }

}
