package com.spms.paymentservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    private Long parkingSpaceId;
    private Long vehicleId;
    private double amount;
    private String cardNumber;
    private String cardHolder;
    
    private String status = "PENDING"; // PENDING, COMPLETED, FAILED, REFUNDED
    
    private LocalDateTime transactionDate;
    private String receiptNumber;

    public Payment() {
    }

    public Payment(Long id, Long userId, Long parkingSpaceId, Long vehicleId, double amount, String cardNumber, String cardHolder, String status, LocalDateTime transactionDate, String receiptNumber) {
        this.id = id;
        this.userId = userId;
        this.parkingSpaceId = parkingSpaceId;
        this.vehicleId = vehicleId;
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.status = status;
        this.transactionDate = transactionDate;
        this.receiptNumber = receiptNumber;
    }

    @PrePersist
    public void prePersist() {
        this.transactionDate = LocalDateTime.now();
        this.receiptNumber = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(Long parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }
}
