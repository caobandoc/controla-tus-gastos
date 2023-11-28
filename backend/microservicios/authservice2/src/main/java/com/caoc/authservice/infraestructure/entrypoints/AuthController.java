package com.caoc.authservice.infraestructure.entrypoints;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
public class AuthController {

    @GetMapping
    public ResponseEntity<Map<String, String>> validate(@RequestHeader("Authorization") String authorization) {
        return ResponseEntity.ok(Map.of("token", authorization.split(" ")[1]));

    }

}
