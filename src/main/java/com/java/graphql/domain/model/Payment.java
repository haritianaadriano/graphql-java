package com.java.graphql.domain.model;

import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private int id;

  private Instant createdAt;

  private boolean isLate;

  @JoinColumn(name = "client_id")
  @ManyToOne
  private Client client;

  @JoinColumn(name = "bank_account_id")
  @ManyToOne
  private BankAccount bankAccount;
}
