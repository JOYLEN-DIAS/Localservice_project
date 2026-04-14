package com.joylen.LocalService.controller;

import com.joylen.LocalService.dto.Loginrequest;
import com.joylen.LocalService.model.User;
import com.joylen.LocalService.service.Authservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class Authcontroller {
    private final Authservice authservice;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        return ResponseEntity.ok(authservice.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Loginrequest request){
        System.out.println("EMAIL: " + request.getEmail());
        System.out.println("PASSWORD: " + request.getPassword());
        return ResponseEntity.ok(authservice.login(request.getEmail(), request.getPassword()));
    }


}
