package com.java.graphql.repository.dao;

import com.java.graphql.domain.model.BankAccount;
import com.java.graphql.domain.model.Client;
import com.java.graphql.domain.model.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PaymentDao {
  private final EntityManager entityManager;

  public List<Payment> findPaymentByClientKeyword(String keyword, Pageable pageable) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Payment.class);
    Root<Payment> root = criteriaQuery.from(Payment.class);

    Join<Payment, Client> principalRoot = root.join("client", JoinType.LEFT);

    Predicate predicate = criteriaBuilder.conjunction();

    Predicate hasClientFirstname = criteriaBuilder.or(
        criteriaBuilder.like(criteriaBuilder.lower(principalRoot.get("firstName")), "%" + keyword + "%"),
        criteriaBuilder.like(principalRoot.get("firstName"), "%" + keyword + "%")
    );

    Predicate hasClientLastname = criteriaBuilder.or(
        criteriaBuilder.like(criteriaBuilder.lower(principalRoot.get("lastName")), "%" + keyword + "%"),
        criteriaBuilder.like(principalRoot.get("lastName"), "%" + keyword + "%")
    );

    predicate = criteriaBuilder.and(hasClientLastname, hasClientFirstname);

    criteriaQuery
        .distinct(true)
        .where(predicate);

    return entityManager.createQuery(criteriaQuery)
        .setFirstResult((pageable.getPageNumber()) * pageable.getPageSize())
        .setMaxResults(pageable.getPageSize())
        .getResultList();
  }

  public List<Payment> findPaymentByBankaccountCriteria(List<String>banks_ids, Instant from, Instant to, Pageable pageable) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Payment.class);
    Root<Payment> root = criteriaQuery.from(Payment.class);

    Join<Payment, BankAccount> bankAccountJoin = root.join("bankAccount", JoinType.LEFT);

    Predicate predicate = criteriaBuilder.conjunction();

    switch (getFilterCase(banks_ids, from, to)) {
      case 1:
        // Only banks_ids
        Expression<String> bankAccountIdExpression = bankAccountJoin.get("id");
        predicate = criteriaBuilder.and(predicate, bankAccountIdExpression.in(banks_ids));
        break;
      case 2:
        // Only from
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), from));
        break;
      case 3:
        // Only to
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), to));
        break;
      case 4:
        // Both from and to
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("createdAt"), from, to));
        break;
      default:
        // No specific case
        break;
    }

    criteriaQuery
        .distinct(true)
        .where(predicate);

    return entityManager.createQuery(criteriaQuery)
        .setFirstResult((pageable.getPageNumber()) * pageable.getPageSize())
        .setMaxResults(pageable.getPageSize())
        .getResultList();
  }

  private int getFilterCase(List<String> banks_ids, Instant from, Instant to) {
    int filterCase = 0;
    if (banks_ids != null && !banks_ids.isEmpty()) {
      filterCase = 1;
    }
    if (from != null) {
      filterCase = 2;
    }
    if (to != null) {
      filterCase = 3;
    }
    if (to != null && from != null) {
      filterCase = 4;
    }
    return filterCase;
  }
}
