package com.java.graphql.service;

import com.java.graphql.domain.model.Payment;
import com.java.graphql.domain.model.enums.PaymentStatus;
import com.java.graphql.repository.PaymentRepository;
import com.java.graphql.repository.dao.PaymentDao;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {
  private final PaymentRepository repository;
  private final PaymentDao dao;

  public Set<Payment> getPayments(
      List<PaymentStatus>paymentStatuses, List<Integer>bankAccountIds,
      Instant from, Instant to, String clientKeyWord, int page, int pageSize
      ) {
    Pageable pageable = PageRequest.of((page-1), pageSize);
    Set<Payment> data =
        new HashSet<>(dao.findPaymentByClientKeyword(clientKeyWord, pageable));

    if(!paymentStatuses.isEmpty()) {
      data.addAll(getPaymentByStatus(paymentStatuses, pageable));
    }
    if(!bankAccountIds.isEmpty()) {
      data.addAll(repository.findByBankAccountCriteria(from, to, bankAccountIds, pageable));
    }
    return data;
  }

  public Set<Payment> getPaymentByStatus(List<PaymentStatus>paymentStatuses, Pageable pageable) {
    Set<Payment> restults = new HashSet<>();
    Map<PaymentStatus, Set<Payment>> eachPaymentStatusValues = new HashMap<>();
    eachPaymentStatusValues.put(PaymentStatus.LATE, repository.findPaymentByLateOrPaid(true, pageable));
    eachPaymentStatusValues.put(PaymentStatus.PAID, repository.findPaymentByLateOrPaid(false, pageable));
    eachPaymentStatusValues.put(PaymentStatus.UNPAID, repository.findPaymentUnpaid(pageable));

    paymentStatuses.forEach(status -> {
      restults.addAll(eachPaymentStatusValues.get(status));
    });
    return restults;
  }
}
