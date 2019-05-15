package com.sustcDB2019.DB104.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
    @RequestMapping("/helloW")
    public String index1() {
        return "Hello Worasdfasfld";
    }
}