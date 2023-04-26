package com.java.graphql.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Client {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
