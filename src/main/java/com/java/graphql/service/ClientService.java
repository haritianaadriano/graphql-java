package com.java.graphql.service;

import com.java.graphql.controller.mapper.ClientMapper;
import com.java.graphql.domain.model.Client;
import com.java.graphql.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository repository;

    public List<Client> getClients(){
        return repository.findAll();
    }

    public List<Client> createClients(List<Client> data){
        return repository.saveAll(data);
    }
}
