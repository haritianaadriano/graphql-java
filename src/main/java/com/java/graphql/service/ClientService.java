package com.java.graphql.service;

import com.java.graphql.controller.mapper.ClientMapper;
import com.java.graphql.domain.model.Client;
import com.java.graphql.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository repository;
    private ClientMapper mapper;

    public Client createClient(String firstName, String lastName, String email, String phoneNumber){
        com.java.graphql.controller.model.Client model = com.java.graphql.controller.model.Client.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .build();
        return repository.save(mapper.toData(model));
    }
}
