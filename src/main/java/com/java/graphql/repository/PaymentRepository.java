package com.java.graphql.repository;

import com.java.graphql.domain.model.Payment;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

  @Query(
      """
    SELECT p FROM Payment p
    WHERE p.createdAt IS NULL
"""
  )
  List<Payment> findPaymentUnpaid(Pageable pageable);

  @Query(
      """
    SELECT p FROM Payment p 
    WHERE p.isLate = true 
"""
  )
  List<Payment> findPaymentLate(
      Pageable pageable
      );

  @Query(
      """
    SELECT p FROM Payment p
    WHERE p.isLate = FALSE
    AND p.createdAt IS NOT NULL
"""
  )
  List<Payment> findPaymentPaid(
      Pageable pageable
  );
}
