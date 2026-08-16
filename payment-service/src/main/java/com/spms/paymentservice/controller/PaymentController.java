package com.spms.paymentservice.controller;

import com.spms.paymentservice.model.Payment;
import com.spms.paymentservice.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment) {
        Payment processedPayment = paymentService.processPayment(payment);
        if ("FAILED".equals(processedPayment.getStatus())) {
            return ResponseEntity.badRequest().body(processedPayment);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(processedPayment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getPaymentsByUser(userId));
    }

    @GetMapping("/{id}/receipt")
    public ResponseEntity<Map<String, Object>> getReceipt(@PathVariable Long id) {
        Map<String, Object> receipt = paymentService.getReceipt(id);
        if (receipt != null) {
            return ResponseEntity.ok(receipt);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/refund")
    public ResponseEntity<Payment> processRefund(@PathVariable Long id) {
        Optional<Payment> refundedPayment = paymentService.processRefund(id);
        if (refundedPayment.isPresent()) {
            return ResponseEntity.ok(refundedPayment.get());
        }
        return paymentService.getPaymentById(id).isPresent() 
            ? ResponseEntity.badRequest().build() 
            : ResponseEntity.notFound().build();
    }
}
