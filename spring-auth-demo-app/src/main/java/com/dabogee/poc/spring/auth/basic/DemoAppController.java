package com.dabogee.poc.spring.auth.basic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@RestController
@RequestMapping("/")
public class DemoAppController {

    private static final LocalDateTime STARTUP_TIME = LocalDateTime.now();

    @Value("${server.port}")
    private String serverPort;

    @GetMapping
    public ResponseEntity<Object> hello() {
        Map<String, Object> output = new HashMap<>();
        output.put("startup_time", ISO_LOCAL_DATE_TIME.format(STARTUP_TIME.truncatedTo(ChronoUnit.SECONDS)));
        output.put("port", serverPort);
        return ResponseEntity.ok(output);
    }
}
