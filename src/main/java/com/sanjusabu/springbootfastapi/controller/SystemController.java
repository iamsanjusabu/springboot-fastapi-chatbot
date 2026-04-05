package com.sanjusabu.springbootfastapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


// *** TEST ONLY ***
@Controller
@RequestMapping("/system")
class SystemController {


    private final RestTemplate restTemplate;

    public SystemController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Status check 1
    @GetMapping("/ping")
    public ResponseEntity<String> pong() {
        return ResponseEntity.ok("pong");
    }

    // Status check 2
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("active");
    }

    // Get session id
    @GetMapping("/session-id")
    public ResponseEntity<String> getSessionId(HttpServletRequest request) {
        return ResponseEntity.ok(request.getSession().getId());
    }

    // Get CSRF token
    // This method doesn't work once CSRF is turned off in Configuration
    @GetMapping("/csrf-token")
    public ResponseEntity<CsrfToken> getCsrfToken(HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");

        if (token == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(token);
    }

    @GetMapping("/fastapi/health")
    public ResponseEntity<String> activeFromFastAPI() {
        return ResponseEntity.ok(restTemplate.getForObject("http://localhost:8000/api/health", String.class));
    }

    @GetMapping("/fastapi/ping")
    public ResponseEntity<String> pongFromFastAPI() {
        return ResponseEntity.ok(restTemplate.getForObject("http://localhost:8000/api/ping", String.class));
    }
}
