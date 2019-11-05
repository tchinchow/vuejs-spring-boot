package net.tchinchow.vuejs_spring_boot.spring_backend.controller.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.tchinchow.vuejs_spring_boot.spring_backend.entity.Greeting;

/**
 * HelloAPIController
 */
@RestController
//@CrossOrigin("http://localhost:3000")
public class HelloAPIController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/api/public/hello")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "${welcome.message}") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}