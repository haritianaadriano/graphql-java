package com.java.graphql.service;

import com.java.graphql.controller.mapper.BankAccountMapper;
import com.java.graphql.domain.model.BankAccount;
import com.java.graphql.domain.model.Status;
import com.java.graphql.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankAccountService {
    private BankAccountRepository repository;

    public List<BankAccount> getBankAccounts(int page, int pageSize){
        Pageable pageable = PageRequest.of(page-1, pageSize);
        return repository.findAll(pageable).stream().toList();
    }
    public List<BankAccount> createBankAccounts(List<BankAccount> data){
        return repository.saveAll(data);
    }
}
