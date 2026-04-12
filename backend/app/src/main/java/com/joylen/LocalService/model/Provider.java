package com.joylen.LocalService.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "providerrpofile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false,unique = true)
    private User user;

    private String servicecategory;
    private Long experience;
    private String servicearea;

    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL)
    private List<Service> services;
    @OneToMany(mappedBy = "provider" , cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
