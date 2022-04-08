package com.example.online_banking.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String role;

    @Column(nullable = false, length = 20)
    @Size(min = 3, max = 30)
    private String fullName;

    @Column(length = 10)
    private String phoneNumber;

    @Email
    @NotEmpty
    @Column(nullable = false,unique = true, length = 45)
    private String email;

    @NotEmpty
    @Column(nullable = false, length = 64)
    private String password;

//    @NotEmpty
//    @Column(nullable = false, length = 20)
//    @Size(min = 3, max = 30)
//    private String userName;
//
//    @Column(nullable = false, length = 20)
//    @Size(min = 3, max = 30)
//    private String firstName;
//
//    @Column(nullable = false, length = 20)
//    private String lastName;
//
//    @Length(min = 5, max = 50)
//    private String address;

    public User() {
        this.id = id;
        this.role = role;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public String getFullName() {return fullName;}

    public void setFullName(String userName) {this.fullName = userName;}

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}
}
