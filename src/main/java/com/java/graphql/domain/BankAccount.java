package com.java.graphql.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class BankAccount {
    UUID id;
    String firstName;
    String lastName;
    String phoneNumber;
    Status status;

    enum Status {
        ENABLE, DISABLE
    }
}
