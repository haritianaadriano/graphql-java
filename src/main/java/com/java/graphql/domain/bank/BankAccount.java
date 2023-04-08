package com.java.graphql.domain.bank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private UUID id;
    private String ref;
    private Client client;
    private Status status;
}
