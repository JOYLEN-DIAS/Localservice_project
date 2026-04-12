package com.joylen.LocalService.repository;

import com.joylen.LocalService.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Servicerepository extends JpaRepository<Service,Long> {
    List<Service> findByProviderId(Long provider_id);

}
