package com.java.graphql.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "bank_account")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String ref;
    @OneToOne
    private Client client;
    @Enumerated(STRING)
    private Status status;
    private Instant openSession;
}
