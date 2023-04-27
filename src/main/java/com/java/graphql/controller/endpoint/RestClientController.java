package com.java.graphql.controller.endpoint;

import com.java.graphql.domain.model.Client;
import com.java.graphql.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RestClientController {
    private ClientService service;

    @GetMapping("/clients")
    public List<Client> getClients(){
        return service
    }
}
