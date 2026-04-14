package com.joylen.LocalService.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Loginrequest {
        private String email;
        private String password;
}
