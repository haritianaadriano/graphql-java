package com.java.graphql.repository.dao;

import com.java.graphql.domain.model.Client;
import com.java.graphql.domain.model.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + keyword + "%"),
        criteriaBuilder.like(root.get("firstName"), "%" + keyword + "%")
    );

    Predicate hasClientLastname = criteriaBuilder.or(
        criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + keyword + "%"),
        criteriaBuilder.like(root.get("lastName"), "%" + keyword + "%")
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
}
