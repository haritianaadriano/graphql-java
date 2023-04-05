package com.java.graphql.resolver;

import com.java.graphql.domain.BankAccount;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BankAccountResolver implements GraphQLQueryResolver {
    public BankAccount bankAccount(UUID id){
        return BankAccount.builder()
                .id(id)
                .lastName("adriano")
                .firstName("haritiana")
                .status(BankAccount.Status.ENABLE)
                .phoneNumber("phone-number")
                .build();
    }
}
