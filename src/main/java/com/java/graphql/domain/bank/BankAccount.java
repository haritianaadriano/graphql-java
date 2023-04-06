package com.java.graphql.domain.bank;

import lombok.Builder;

import java.util.UUID;

@Builder
public record BankAccount (
        UUID id,
        String firstName,
        String lastName,
        String phoneNumber,
        Status status
){
}
