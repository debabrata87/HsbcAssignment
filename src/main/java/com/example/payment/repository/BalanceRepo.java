package com.example.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.payment.model.Balance;


public interface BalanceRepo extends JpaRepository<Balance, Integer>{

	List<Balance> findByCurrency(String currency);
}
