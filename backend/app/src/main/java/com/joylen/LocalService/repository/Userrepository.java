package com.joylen.LocalService.repository;

import com.joylen.LocalService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Userrepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);


}
