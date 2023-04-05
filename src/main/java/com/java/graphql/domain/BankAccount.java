package com.java.graphql.domain;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class BankAccount {
    UUID id;
    String firstName;
    String lastName;
    String phoneNumber;
    Status status;

    public enum Status {
        ENABLE, DISABLE
    }
}
