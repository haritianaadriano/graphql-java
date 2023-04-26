package com.java.graphql.controller.mapper;

import com.java.graphql.domain.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client toData(com.java.graphql.controller.model.Client model){
        return Client.builder()
                .email(model.getEmail())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .phoneNumber(model.getPhoneNumber())
                .build();
    }
}
