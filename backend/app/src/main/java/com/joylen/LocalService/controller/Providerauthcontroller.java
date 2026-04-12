package com.joylen.LocalService.controller;

import com.joylen.LocalService.dto.Providerrequestdto;
import com.joylen.LocalService.model.Provider;
import com.joylen.LocalService.service.Providerauthservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/providerauth")
@RequiredArgsConstructor
public class Providerauthcontroller {
    private final Providerauthservice providerauth;

    @PostMapping("/providerregister")
    public ResponseEntity<String> providerregister(@RequestParam Long userid,@RequestBody Providerrequestdto dto){
        return ResponseEntity.ok(providerauth.registerprovider(userid,dto));
    }
}
