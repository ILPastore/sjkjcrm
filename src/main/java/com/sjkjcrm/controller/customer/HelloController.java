package com.sjkjcrm.controller.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello, world.";
    }
}
