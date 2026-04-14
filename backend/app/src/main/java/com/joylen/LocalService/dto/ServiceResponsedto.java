package com.joylen.LocalService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceResponsedto {

    private Long id;
    private String servicetitle;
    private double price;
    private boolean isAvailable;
    // Optional (useful for frontend)
    private Long providerId;
}
