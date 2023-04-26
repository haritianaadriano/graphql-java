package com.java.graphql.controller;

import com.java.graphql.domain.model.BankAccount;
import com.java.graphql.domain.model.Status;
import com.java.graphql.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class BankAccountController {
    private BankAccountService service;

    @QueryMapping
    public List<BankAccount> bankAccount(){
        return service.getBankAccounts();
    }

    @MutationMapping
    public BankAccount createBankAccount(
            @Argument String ref,
            @Argument Status status,
            @Argument int client
    ){
        return service.createBankAccounts(ref, status, client);
    }
}
