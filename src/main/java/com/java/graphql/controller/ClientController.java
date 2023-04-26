package com.java.graphql.controller;

import com.java.graphql.domain.model.Client;
import com.java.graphql.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ClientController {
    private ClientService service;

    @MutationMapping
    public Client createClient(
            @Argument String firstName,
            @Argument String lastName,
            @Argument String email,
            @Argument String phoneNumber
    ){
        return service.createClient(firstName, lastName, email, phoneNumber);
    }
}
