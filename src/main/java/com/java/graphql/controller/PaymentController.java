package com.java.graphql.controller;

import com.java.graphql.domain.model.Payment;
import com.java.graphql.domain.model.enums.PaymentStatus;
import com.java.graphql.service.PaymentService;
import java.time.Instant;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PaymentController {
  private final PaymentService service;

  @GetMapping("/payments")
  public List<Payment> getPayments(
      @RequestParam(name = "page")Integer page, @RequestParam(name = "page_size")Integer pageSize,
      @RequestParam(name = "client_key_word", required = false, defaultValue = "")String clientKeyword,
      @RequestParam(name = "from", required = false)Instant from, @RequestParam(name = "to", required = false)Instant to,
      @RequestParam(name = "bankaccounts_ids", required = false)List<Integer> bankaccountsIds,
      @RequestParam(name = "payments_statuses", required = false)List<PaymentStatus> paymentStatuses
      ) {
    List<Payment> toShow =
        new ArrayList<>(service.getPayments(paymentStatuses, bankaccountsIds, from, to, clientKeyword, page, pageSize));
    return toShow;
  }
}
