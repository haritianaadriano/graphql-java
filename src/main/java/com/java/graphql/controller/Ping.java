package com.java.graphql.controller;

import com.java.graphql.domain.bank.BankAccount;
import com.java.graphql.domain.bank.Status;
import com.java.graphql.domain.bank.ping.Ping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
public class BankAccountController{
    @QueryMapping
    public Ping ping(){
        return Ping.pong;
    }
}
