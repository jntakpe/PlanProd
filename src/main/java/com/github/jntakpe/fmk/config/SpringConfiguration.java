package com.github.jntakpe.fmk.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Classe mère de l'application et configuration de Spring
 *
 * @author jntakpe
 */
@Configuration
@ComponentScan("com.github.jntakpe")
@EnableAutoConfiguration
public class SpringConfiguration extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringConfiguration.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringConfiguration.class);
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages/gui-i18n", "classpath:/messages/gui-messages", "classpath:/messages/log-messages");
        messageSource.setCacheSeconds(10);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
