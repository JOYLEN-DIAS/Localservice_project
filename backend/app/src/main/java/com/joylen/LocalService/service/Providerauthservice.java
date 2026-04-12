package com.joylen.LocalService.service;

import com.joylen.LocalService.dto.Providerrequestdto;
import com.joylen.LocalService.enums.Role;
import com.joylen.LocalService.model.Provider;
import com.joylen.LocalService.model.User;
import com.joylen.LocalService.repository.Providerrepository;
import com.joylen.LocalService.repository.Userrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class Providerauthservice {
    private final Providerrepository providerrepository;
    private final Userrepository userrepository;

    public String registerprovider(Long userid, Providerrequestdto dto){
        User user = userrepository.findById(userid).orElseThrow(()->new RuntimeException("user not found"));
        Provider provider = Provider.builder()
                .user(user)
                .servicecategory(dto.getServicecategory())
                .servicearea(dto.getServicearea())
                .experience(dto.getExperience())
                .build();
        providerrepository.save(provider);

        if(user.getRoles() == null){
            user.setRoles(new ArrayList<>());
        }
        if(!user.getRoles().contains(Role.PROVIDER)){
            user.getRoles().add(Role.PROVIDER);
            userrepository.save(user);
        }

        return "provider saved successfull";
    }
}
