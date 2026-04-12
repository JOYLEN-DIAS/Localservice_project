package com.joylen.LocalService.service;

import com.joylen.LocalService.dto.Servicerequestdto;
import com.joylen.LocalService.model.Provider;
import com.joylen.LocalService.model.Service;
import com.joylen.LocalService.model.User;
import com.joylen.LocalService.repository.Providerrepository;
import com.joylen.LocalService.repository.Servicerepository;
import com.joylen.LocalService.repository.Userrepository;
import com.joylen.LocalService.security.Jwtutil;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class Providerservice {
    private final Providerrepository providerRepository;   // ✅ ADD THIS
    private final Servicerepository servicerepository;
    private final Userrepository userrepository;
    private final Jwtutil jwtutil;

    public Service addservice(String token, Servicerequestdto dto){
// 🔥 Remove Bearer
        token = token.substring(7);

        //  Extract email
        String email = jwtutil.extractEmail(token);

        //  Find user
        User user = userrepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  Get provider
        Provider provider = providerRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        Service service = Service.builder()
                .servicetitle(dto.getTitle())
                .price(dto.getPrice())
                .isAvailable(true)
                .provider(provider)
                .build();

        return servicerepository.save(service);
    }

    public List<Service> getproviderservice(Long provider_id){
        return servicerepository.findByProviderId(provider_id);
    }

    public List<Service> getallservice(){
        return servicerepository.findAll();
    }
}
