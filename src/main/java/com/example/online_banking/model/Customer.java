package com.example.online_banking.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 12)
    @Size(min = 9, max = 12)
    private String SSN;

    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 30)
    private String streetAddress;

    @Column(nullable = false, length = 25)
    @Size(min = 3, max = 20)
    private String city;

    @Column(nullable = false, length = 25)
    @Size(min = 3, max = 20)
    private String district;

    public Customer() {}

    public Customer(Long id) {
        this.id = id;
    }

    public Customer(String role, String fullName, String phoneNumber, String email, String password, String SSN, String streetAddress, String city, String district) {
        super(role, fullName, phoneNumber, email, password);
        this.SSN = SSN;
        this.streetAddress = streetAddress;
        this.city = city;
        this.district = district;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
