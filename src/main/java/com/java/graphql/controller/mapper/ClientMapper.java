package com.java.graphql.controller.mapper;

import com.java.graphql.domain.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client toData(com.java.graphql.controller.model.Client parameter){
        return Client.builder()
                .email(parameter.getEmail())
                .firstName(parameter.getFirstName())
                .lastName(parameter.getLastName())
                .phoneNumber(parameter.getPhoneNumber())
                .build();
    }
}
