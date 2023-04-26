package com.java.graphql.controller.model;

import com.java.graphql.domain.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccount {
    private String ref;
    private Status status;
    private int client;
}
