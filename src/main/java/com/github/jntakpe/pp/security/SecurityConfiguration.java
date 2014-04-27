package com.github.jntakpe.pp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration de Spring security
 *
 * @author jntakpe
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint;

    @Autowired
    private AjaxAuthenticationSuccessHandler successHandler;

    @Autowired
    private AjaxAuthenticationFailureHandler failureHandler;

    @Autowired
    private AjaxLogoutSuccessHandler logoutSuccessHandler;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.inMemoryAuthentication()
                .withUser("jntakpe").password("test").roles("ADMIN", "USER").and()
                .withUser("cegiraud").password("test").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/index.html")
                .antMatchers("/bower_components/**")
                .antMatchers("/css/**")
                .antMatchers("/img/**")
                .antMatchers("/js/**")
                .antMatchers("/views/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(http401UnauthorizedEntryPoint).and()
                .rememberMe().and()
                .formLogin().loginProcessingUrl("/login").loginPage("/").successHandler(successHandler)
                .failureHandler(failureHandler).permitAll().and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler).deleteCookies("JSESSIONID")
                .permitAll().and()
                .csrf().disable()
                .headers().disable()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                .antMatchers("/health").hasAuthority(Authorities.ROLE_ADMIN.name())
                .antMatchers("/dump").hasAuthority(Authorities.ROLE_ADMIN.name())
                .antMatchers("/trace").hasAuthority(Authorities.ROLE_ADMIN.name())
                .antMatchers("/info").hasAuthority(Authorities.ROLE_ADMIN.name())
                .antMatchers("/beans").hasAuthority(Authorities.ROLE_ADMIN.name())
                .antMatchers("/autoconfig").hasAuthority(Authorities.ROLE_ADMIN.name())
                .antMatchers("/env").hasAuthority(Authorities.ROLE_ADMIN.name())
                .antMatchers("/env/**").hasAuthority(Authorities.ROLE_ADMIN.name())
                .antMatchers("/metrics").hasAuthority(Authorities.ROLE_ADMIN.name())
                .antMatchers("/metrics/**").hasAuthority(Authorities.ROLE_ADMIN.name())
                .antMatchers("/mappings").hasAuthority(Authorities.ROLE_ADMIN.name())
                .antMatchers("/configprops").hasAuthority(Authorities.ROLE_ADMIN.name());
    }
}
