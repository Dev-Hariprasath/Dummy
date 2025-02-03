package com.dev.walletX.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "FLOAT DEFAULT 0.0")
    private double balance;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users user;

    public Account() {}

    public Account(Long id, double balance, Users user) {
        this.id = id;
        this.balance = balance;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}

