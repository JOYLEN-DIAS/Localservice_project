package com.joylen.LocalService.controller;

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
    public ResponseEntity<String> login(@RequestParam String email,@RequestParam String password){
        return ResponseEntity.ok(authservice.login(email,password));
    }


}
