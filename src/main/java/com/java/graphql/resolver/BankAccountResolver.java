package com.java.graphql.resolver;

import com.java.graphql.domain.bank.BankAccount;
import com.java.graphql.domain.bank.Status;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class BankAccountResolver implements GraphQLQueryResolver {
    public BankAccount bankAccount(UUID id){
        return BankAccount.builder()
                .id(id)
                .lastName("adriano")
                .firstName("haritiana")
                .status(Status.ENABLE)
                .phoneNumber("phone-number")
                .build();
    }
}
