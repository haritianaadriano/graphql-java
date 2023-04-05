package com.java.graphql.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Ping {
    @QueryMapping
    public com.java.graphql.domain.bank.ping.Ping ping(){
        return com.java.graphql.domain.bank.ping.Ping.pong;
    }
}
