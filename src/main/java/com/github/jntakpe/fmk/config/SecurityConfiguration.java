package com.github.jntakpe.fmk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration de Spring security
 *
 * @author jntakpe
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN").and()
                .withUser("user").password("user").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/bower_components/**")
                .antMatchers("/css/**")
                .antMatchers("/img/**")
                .antMatchers("/js/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                formLogin().loginProcessingUrl("/login-process").loginPage("/login").defaultSuccessUrl("/home", true).
                failureUrl("/login?error=authFailure").permitAll().and().
                logout().logoutUrl("/logout-process").logoutSuccessUrl("/login?logout=success").deleteCookies("JSESSIONID").permitAll().
                and().
                csrf().disable().
                authorizeRequests().
                antMatchers("/**").authenticated().
                antMatchers("/admin/**").hasAuthority(Authority.ROLE_ADMIN.name());
    }
}
