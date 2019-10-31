package net.tchinchow.vuejs_spring_boot.spring_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ForwardingController
 */
//@Controller
//@RequestMapping("/webapp")
public class ForwardingController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
    // Required because of 'mode: history' usage in frontend routing, see README for further details
    // @RequestMapping(value = "{_:^(?!index\\.html|api).$}")
    @GetMapping(value = "/webapp/**")
    public String redirectApi() {
        logger.info("Redirect triggered");
        return "index.html";
    }
}