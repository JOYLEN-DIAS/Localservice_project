package com.joylen.LocalService.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Passwordencoder{

    public BCryptPasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
    }
}
