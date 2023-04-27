package com.java.graphql.controller.endpoint;

import com.java.graphql.controller.mapper.ClientMapper;
import com.java.graphql.domain.model.Client;
import com.java.graphql.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class RestClientController {
    private ClientService service;
    private ClientMapper mapper;

    @GetMapping("/clients")
    public List<Client> getClients(){
        return service.getClients().stream()
                .collect(Collectors.toUnmodifiableList());
    }

    @PostMapping("/clients")
    public List<Client> createClients(@RequestBody List<com.java.graphql.controller.model.Client> data){
        List<Client> toCreate = data.stream().map(mapper::toData)
                .collect(Collectors.toUnmodifiableList());
        return service.createClients(toCreate)
                .stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
