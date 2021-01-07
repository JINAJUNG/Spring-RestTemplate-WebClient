package com.example.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
    @GetMapping("/hello")
    public ResponseEntity getHello(Integer second) throws InterruptedException {
        Thread.sleep(second);
        return ResponseEntity.ok("Hello"+second);
    }
}
