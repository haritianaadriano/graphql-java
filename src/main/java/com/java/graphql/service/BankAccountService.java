package com.java.graphql.service;

import com.java.graphql.domain.model.BankAccount;
import com.java.graphql.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankAccountService {
    private BankAccountRepository repository;

    public List<BankAccount> getBankAccounts(){
        return repository.findAll();
    }
    public List<BankAccount> createBankAccounts(){

    }
}
