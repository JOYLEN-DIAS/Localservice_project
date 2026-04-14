package com.joylen.LocalService.service;

import com.joylen.LocalService.dto.ServiceResponsedto;
import com.joylen.LocalService.dto.Servicerequestdto;
import com.joylen.LocalService.model.Provider;
import com.joylen.LocalService.model.Service;
import com.joylen.LocalService.model.User;
import com.joylen.LocalService.repository.Providerrepository;
import com.joylen.LocalService.repository.Servicerepository;
import com.joylen.LocalService.repository.Userrepository;
import com.joylen.LocalService.security.Jwtutil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class Providerservice {

    private final Servicerepository servicerepository;
    private final CurrentUserservice currentUserservice;
    public Service addservice(Servicerequestdto dto){

        Provider provider = currentUserservice.getCurrentProvider();

        Service service = Service.builder()
                .servicetitle(dto.getTitle())
                .price(dto.getPrice())
                .isAvailable(true)
                .provider(provider)
                .build();

        return servicerepository.save(service);
    }

    //for a normal user to see the provider service
    public List<ServiceResponsedto> getproviderservice(Long provider_id){
        return servicerepository.findByProviderId(provider_id).stream().map(
                service -> new ServiceResponsedto(
                        service.getId(),
                        service.getServicetitle(),
                        service.getPrice(),
                        service.isAvailable(),
                        service.getProvider().getId()
                )
        ).toList();
    }

    public List<ServiceResponsedto> getallservice(){
        return servicerepository.findAll().stream().map(
                service -> new ServiceResponsedto(
                        service.getId(),
                        service.getServicetitle(),
                        service.getPrice(),
                        service.isAvailable(),
                        service.getProvider().getId()
                )
        ).toList();
    }
}
