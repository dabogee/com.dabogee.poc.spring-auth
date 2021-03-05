package com.dabogee.poc.spring.auth.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Simple spring application with a single admin user.
 * Basic auth is used.
 *
 * User:    demo
 * Pwd:     demo1
 */
@SpringBootApplication
public class DemoApp {

    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }
}
