package com.java.graphql.controller.endpoint;

import com.java.graphql.domain.model.BankAccount;
import com.java.graphql.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RestBankaccountController {
    private BankAccountService service;

    @GetMapping("/bankaccounts")
    public List<BankAccount> findBankAccount(){
        return service.getBankAccounts();
    }
}
