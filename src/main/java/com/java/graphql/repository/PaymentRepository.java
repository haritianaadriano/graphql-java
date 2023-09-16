package com.java.graphql.repository;

import com.java.graphql.domain.model.Payment;
import java.time.Instant;
import java.util.Set;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
  @Query(
      "SELECT p FROM Payment p " +
      "JOIN p.bankAccount b " +
      "WHERE b.id IN :bank_ids " +
      "AND b.openSession BETWEEN :from AND :to"
  )
  Set<Payment> findByBankAccountCriteria(
      @Param("from")Instant from,
      @Param("to")Instant to,
      @Param("bank_ids")List<Integer>bankAccountsIds,
      Pageable pageable
      );

  @Query(
      "SELECT p FROM Payment p " +
      "WHERE p.createdAt IS NULL"
  )
  Set<Payment> findPaymentUnpaid(Pageable pageable);

  @Query(
      "SELECT p FROM Payment p " +
      "WHERE p.isLate = :lateStatus"
  )
  Set<Payment> findPaymentByLateOrPaid(
      @Param("lateStatus")boolean lateStatus,
      Pageable pageable
      );
}
