package com.joylen.LocalService.service;

import com.joylen.LocalService.model.Provider;
import com.joylen.LocalService.model.User;
import com.joylen.LocalService.repository.Providerrepository;
import com.joylen.LocalService.repository.Userrepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CurrentUserservice {
    private final Userrepository userrepository;
    private final Providerrepository providerRepository;

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        System.out.println("AUTH: " + auth);
        System.out.println("IS AUTHENTICATED: " + auth.isAuthenticated());
        System.out.println("PRINCIPAL: " + auth.getName());
        System.out.println("Authorities: " + auth.getAuthorities());

        return userrepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Provider getCurrentProvider() {
        User user = getCurrentUser();

        return providerRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));
    }
}
