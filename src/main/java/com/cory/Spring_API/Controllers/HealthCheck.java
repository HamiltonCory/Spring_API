package com.cory.Spring_API.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/health")
public class HealthCheck {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity checkHealth() {
        return ResponseEntity.ok().build();
    }
}