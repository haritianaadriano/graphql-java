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
import java.util.stream.Collectors;
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
      List<PaymentStatus>paymentStatuses, List<String>bankAccountIds,
      Instant from, Instant to, String clientKeyWord, int page, int pageSize
      ) {
    Pageable pageable = PageRequest.of((page-1), pageSize);
    Set<Payment> result = new HashSet<>();
    Set<Payment> data =
        new HashSet<>(dao.findPaymentByClientKeyword(clientKeyWord, pageable));

    if(bankAccountIds != null && !bankAccountIds.isEmpty()) {
      result = filterPaymentfromTwoSet(
          data,
          new HashSet<>(dao.findPaymentByBankaccountCriteria(bankAccountIds, from, to, pageable)));
    }
    if(paymentStatuses != null && !paymentStatuses.isEmpty()) {
      result = filterPaymentfromTwoSet(
          data,
          new HashSet<>(getPaymentByStatus(paymentStatuses, pageable)));
    }
    else {
      result = data;
    }
    return result;
  }

  public Set<Payment> getPaymentByStatus(List<PaymentStatus>paymentStatuses, Pageable pageable) {
    Set<Payment> restults = new HashSet<>();
    Map<PaymentStatus, List<Payment>> eachPaymentStatusValues = new HashMap<>();
    eachPaymentStatusValues.put(PaymentStatus.LATE, repository.findPaymentLate(pageable));
    eachPaymentStatusValues.put(PaymentStatus.PAID, repository.findPaymentPaid(pageable));
    eachPaymentStatusValues.put(PaymentStatus.UNPAID, repository.findPaymentUnpaid(pageable));

    paymentStatuses.forEach(status -> {
      restults.addAll(eachPaymentStatusValues.get(status));
    });
    return restults;
  }

  public Set<Payment> filterPaymentfromTwoSet(Set<Payment> givenData, Set<Payment> toCompare) {
    return givenData.stream().filter(toCompare::contains).collect(Collectors.toSet());
  }
}
