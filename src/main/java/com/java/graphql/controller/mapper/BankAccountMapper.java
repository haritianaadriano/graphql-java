package com.java.graphql.controller.mapper;

import com.java.graphql.domain.model.BankAccount;
import com.java.graphql.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BankAccountMapper {
    private ClientRepository clientRepository;
    public BankAccount toData(com.java.graphql.controller.model.BankAccount parameter){
        return BankAccount.builder()
                .ref(parameter.getRef())
                .status(parameter.getStatus())
                .client(clientRepository.findById(parameter.getClient()).get())
                .build();
    }
}
