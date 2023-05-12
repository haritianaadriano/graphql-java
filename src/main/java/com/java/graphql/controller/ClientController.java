package com.java.graphql.controller;

import com.java.graphql.controller.mapper.ClientMapper;
import com.java.graphql.domain.model.Client;
import com.java.graphql.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ClientController {
    private ClientService service;
    private ClientMapper mapper;

    @MutationMapping
    public List<Client> createClient(
            @Argument(name = "createClient") List<com.java.graphql.controller.model.Client> data
    ){
        List<Client> toCreate = data.stream()
                .map(mapper::toData)
                .collect(Collectors.toUnmodifiableList());
        return service.createClients(toCreate)
                .stream().collect(Collectors.toUnmodifiableList());
    }

    @QueryMapping
    public List<Client> getClients(){
        return service.getClients();
    }
}
