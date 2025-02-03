package com.dev.walletX.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transactions {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_account_id", nullable = false)
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id", nullable = false)
    private Account receiverAccount;

    @Column(nullable = false)
    private double amount;

    private LocalDateTime timestamp = LocalDateTime.now();

    @Column
    private String description;

    public Transactions() {
    }

    public Transactions(Long id, Account senderAccount, Account receiverAccount, double amount, LocalDateTime timestamp, String description) {
        this.id = id;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        this.timestamp = timestamp;
        this.description = description;
    }

    public Transactions(Account senderAccount, Account receiverAccount, double amount, LocalDateTime timestamp, String description) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        this.timestamp = timestamp;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
