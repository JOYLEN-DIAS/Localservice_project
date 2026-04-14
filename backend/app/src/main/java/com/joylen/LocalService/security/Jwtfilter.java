package com.joylen.LocalService.security;

import com.joylen.LocalService.model.User;
import com.joylen.LocalService.repository.Userrepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Jwtfilter extends OncePerRequestFilter {
    private final Jwtutil jwtutil;
    private final Userrepository userrepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("🔥 JWT FILTER CALLED");

        String authheader = request.getHeader("Authorization");
        System.out.println("HEADER: " + authheader);

        if (authheader == null || !authheader.startsWith("Bearer ")) {
            System.out.println("❌ No token, continuing...");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authheader.substring(7);
            String email = jwtutil.extractEmail(token);

            System.out.println("✅ EMAIL FROM TOKEN: " + email);

            User user = userrepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            System.out.println("✅ USER FOUND: " + user.getEmail());
            System.out.println("✅ ROLES: " + user.getRoles());

            var authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.name()))
                    .toList();

            System.out.println("✅ AUTHORITIES: " + authorities);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            null,
                            authorities
                    );

            SecurityContextHolder.getContext().setAuthentication(authToken);

        } catch (Exception e) {
            System.out.println("❌ ERROR IN FILTER: " + e.getMessage());
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }
}
