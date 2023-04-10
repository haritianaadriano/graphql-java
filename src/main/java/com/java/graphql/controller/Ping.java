package com.java.graphql.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Ping {
    @QueryMapping
    public com.java.graphql.domain.model.ping.Ping ping(){
        return com.java.graphql.domain.model.ping.Ping.pong;
    }
}
