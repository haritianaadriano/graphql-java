package com.java.graphql.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "\"client\"")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
