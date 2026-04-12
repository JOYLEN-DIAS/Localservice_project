package com.joylen.LocalService.repository;

import com.joylen.LocalService.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Providerrepository extends JpaRepository<Provider,Long>
{
    Optional<Provider> findByUserId(Long userId);

}
