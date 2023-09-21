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

    if (banks_ids != null && !banks_ids.isEmpty()) {
      Expression<String> bankAccountIdExpression = bankAccountJoin.get("id");
      predicate = criteriaBuilder.and(predicate, bankAccountIdExpression.in(banks_ids));
    }

    if (from != null && to == null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), from));
    }

    if (from == null && to != null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), to));
    }

    if (from != null && to != null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("createdAt"), from, to));
    }

    criteriaQuery
        .distinct(true)
        .where(predicate);

    return entityManager.createQuery(criteriaQuery)
        .setFirstResult((pageable.getPageNumber()) * pageable.getPageSize())
        .setMaxResults(pageable.getPageSize())
        .getResultList();
  }
}
