package net.tchinchow.vuejs_spring_boot.spring_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ForwardingController
 */
//@Controller
//@RequestMapping("/webapp")
//TODO: unused... just here for example... it may be ok to remove soon
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