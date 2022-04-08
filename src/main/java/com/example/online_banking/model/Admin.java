package com.example.online_banking.model;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Admin() {}

    public Admin(Long id) {
        this.id = id;
    }

    public Admin(String role, String fullName, String phoneNumber, String email, String password) {
        super(role, fullName, phoneNumber, email, password);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
