package com.example.online_banking.repository;

import com.example.online_banking.model.Loans;
import com.example.online_banking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoansRepository extends JpaRepository<Loans, Long> {
    List<Loans> findBySSN(String SSN);
}
