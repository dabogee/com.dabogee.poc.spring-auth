package com.dabogee.poc.spring.auth.basic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * Based on
 * https://www.baeldung.com/spring-security-login
 * https://www.baeldung.com/spring-security-5-default-password-encoder
 *
 * By default spring-security has an own implementation of login page.
 * Basic Auth configuration.
 */
@Configuration
@EnableWebSecurity
public class DemoAppSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private static final Pbkdf2PasswordEncoder PWD_ENCODER = new Pbkdf2PasswordEncoder();

    @Value("${user.demo.username}")
    private String username;

    @Value("${user.demo.pwd.hash}")
    private String pwdHash;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(PWD_ENCODER)
                .withUser(username)
                .password(pwdHash)
                .roles("USER");     // let it be..
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .defaultSuccessUrl("/", true);
    }

    /**
     * Generates some hashes for .properties file.
     */
    public static void main(String[] args) {
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
        System.out.println("Hashes:");
        for (int i = 0; i < 3; i++) {
            System.out.println(encoder.encode("demo1"));
        }
    }
}
