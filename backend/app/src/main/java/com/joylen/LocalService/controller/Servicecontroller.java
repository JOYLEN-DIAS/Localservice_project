package com.joylen.LocalService.controller;

import com.joylen.LocalService.dto.Servicerequestdto;
import com.joylen.LocalService.model.Service;
import com.joylen.LocalService.service.Providerservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service")
@RequiredArgsConstructor
public class Servicecontroller {
    private final Providerservice providerservice;

    //provider adds his services
    @PostMapping
    public ResponseEntity<Service> addservice(@RequestHeader("Authorization") String token, @RequestBody Servicerequestdto dto){
        return ResponseEntity.ok(providerservice.addservice(token,dto));
    }

    @GetMapping
    public ResponseEntity<List<Service>> getallservice(){
        return ResponseEntity.ok(providerservice.getallservice());
    }

    @GetMapping("/provider/{provider_id}")
    public ResponseEntity<List<Service>> getproviderservice(@PathVariable Long provider_id){
        return ResponseEntity.ok(providerservice.getproviderservice(provider_id));
    }
}
