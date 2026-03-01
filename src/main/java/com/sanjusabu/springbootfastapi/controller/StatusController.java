package com.sanjusabu.springbootfastapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/ping")
class StatusController {
    @GetMapping
    public String pong() {
        return "pong";
    }
}
