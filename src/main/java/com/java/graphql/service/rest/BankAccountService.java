package com.java.graphql.service.rest;

import com.java.graphql.domain.model.BankAccount;
import com.java.graphql.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankAccountService {
    private BankAccountRepository repository;

    public List<BankAccount> findBankAccounts(){
        return repository.findAll();
    }
}
