package com.spms.paymentservice.service;

import com.spms.paymentservice.model.Payment;
import com.spms.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(Payment payment) {
        if (payment.getCardNumber() == null || payment.getCardNumber().length() != 16) {
            payment.setStatus("FAILED");
            return paymentRepository.save(payment);
        }
        payment.setStatus("COMPLETED");
        return paymentRepository.save(payment);
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByUser(Long userId) {
        return paymentRepository.findByUserId(userId);
    }

    public Map<String, Object> getReceipt(Long id) {
        Optional<Payment> paymentOpt = paymentRepository.findById(id);
        if (paymentOpt.isPresent()) {
            Payment p = paymentOpt.get();
            Map<String, Object> receipt = new HashMap<>();
            receipt.put("receiptNumber", p.getReceiptNumber());
            receipt.put("amount", p.getAmount());
            receipt.put("status", p.getStatus());
            receipt.put("date", p.getTransactionDate());
            receipt.put("cardHolder", p.getCardHolder());
            String maskedCard = "****-****-****-" + (p.getCardNumber() != null && p.getCardNumber().length() == 16 ? p.getCardNumber().substring(12) : "0000");
            receipt.put("maskedCard", maskedCard);
            return receipt;
        }
        return null;
    }

    public Optional<Payment> processRefund(Long id) {
        Optional<Payment> paymentOpt = paymentRepository.findById(id);
        if (paymentOpt.isPresent()) {
            Payment p = paymentOpt.get();
            if ("COMPLETED".equals(p.getStatus())) {
                p.setStatus("REFUNDED");
                return Optional.of(paymentRepository.save(p));
            }
        }
        return Optional.empty();
    }
}
