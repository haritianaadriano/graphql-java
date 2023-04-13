package com.java.graphql.controller;

import com.java.graphql.domain.model.BankAccount;
import com.java.graphql.service.BankAccountService;
import lombok.AllArgsConstructor;
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
}
