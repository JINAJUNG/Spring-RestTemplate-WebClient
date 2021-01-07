package com.example.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
    @GetMapping("/hello")
    public ResponseEntity getHello(Integer millis) throws InterruptedException {
        Thread.sleep(millis);
        return ResponseEntity.ok("Hello"+millis);
    }
}
