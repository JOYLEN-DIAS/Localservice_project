package com.joylen.LocalService.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "services")
@Builder
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String servicetitle;
    private double price;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "provider_id",nullable = false)
    private Provider provider;
}
