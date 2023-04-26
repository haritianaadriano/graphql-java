package com.java.graphql.service;

import com.java.graphql.controller.mapper.BankAccountMapper;
import com.java.graphql.domain.model.BankAccount;
import com.java.graphql.domain.model.Status;
import com.java.graphql.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankAccountService {
    private BankAccountRepository repository;
    private BankAccountMapper mapper;

    public List<BankAccount> getBankAccounts(){
        return repository.findAll();
    }
    public BankAccount createBankAccounts(String ref, Status status, int client){
        com.java.graphql.controller.model.BankAccount model = com.java.graphql.controller.model.BankAccount.builder()
                .client(client)
                .status(status)
                .ref(ref)
                .build();
        return repository.save(mapper.toData(model));
    }
}
