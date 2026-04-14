package com.joylen.LocalService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Servicesavedto {
    private Long id;
    private String serviceTitle;
    private double price;
    private boolean isAvailable;
}
