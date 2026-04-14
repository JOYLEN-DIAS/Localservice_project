package com.joylen.LocalService.service;

import com.joylen.LocalService.enums.Role;
import com.joylen.LocalService.model.User;
import com.joylen.LocalService.repository.Userrepository;
import com.joylen.LocalService.security.Jwtutil;
import com.joylen.LocalService.util.Passwordencoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Authservice {
    private final Userrepository userrepository;
    private final Jwtutil jwtutil;
    private final Passwordencoder passwordencoder;

    public String register(User user){

        List<Role> roles = user.getRoles();

        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(Role.USER);


        String encodepassword = passwordencoder.passwordencoder().encode(user.getPassword());
        user.setPassword(encodepassword);
        user.setRoles(roles);
        userrepository.save(user);
        return jwtutil.generatetoken(user.getEmail(),user.getRoles().toString());
    }

    public String login(String email,String password){
        User user = userrepository.findByEmail(email).orElseThrow(()->new RuntimeException("user not found"));

//        String checkpassword = passwordencoder.passwordencoder().encode(password);
        if(!passwordencoder.passwordencoder().matches(password, user.getPassword())){
            throw new RuntimeException("invalid password");
        }

        return jwtutil.generatetoken(user.getEmail(), user.getRoles().getFirst().toString());
    }
    public User getLoggedInUser(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = jwtutil.extractEmail(token);

        return userrepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
