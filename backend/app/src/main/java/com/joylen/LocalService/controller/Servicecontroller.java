package com.joylen.LocalService.controller;

import com.joylen.LocalService.dto.ServiceResponsedto;
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
    public ResponseEntity<Service> addservice(@RequestBody Servicerequestdto dto){
        System.out.println("CONTROLLER HIT");
        return ResponseEntity.ok(providerservice.addservice(dto));
    }

    @GetMapping
    public ResponseEntity<List<ServiceResponsedto>> getallservice(){
        System.out.println("CONTROLLER HIT");
        return ResponseEntity.ok(providerservice.getallservice());
    }

    @GetMapping("/provider/{provider_id}")
    public ResponseEntity<List<ServiceResponsedto>> getproviderservice(@PathVariable Long provider_id){
        System.out.println("CONTROLLER HIT");
        return ResponseEntity.ok(providerservice.getproviderservice(provider_id));
    }
}
