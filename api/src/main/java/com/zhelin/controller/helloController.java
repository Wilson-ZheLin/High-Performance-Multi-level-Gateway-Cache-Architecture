package com.zhelin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @GetMapping("hello")
    public Object hello() {
        return "Hello";
    }
}