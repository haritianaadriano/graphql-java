package com.java.graphql.controller;

import com.java.graphql.controller.mapper.BankAccountMapper;
import com.java.graphql.domain.model.BankAccount;
import com.java.graphql.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class BankAccountController {
    private BankAccountService service;
    private BankAccountMapper mapper;

    @QueryMapping
    public List<BankAccount> getBankaccounts(
            @Argument(name = "page")int page,
            @Argument(name = "page_size")int pageSize
    ){
        return service.getBankAccounts(page, pageSize);
    }

    @MutationMapping
    public List<BankAccount> createBankAccount(
            @Argument(name = "createBankAccount") List<com.java.graphql.controller.model.BankAccount> data
    ){
        List<BankAccount> toCreate = data.stream()
                .map(mapper::toData)
                .collect(Collectors.toUnmodifiableList());
        return service.createBankAccounts(toCreate)
                .stream().collect(Collectors.toUnmodifiableList());
    }
}
