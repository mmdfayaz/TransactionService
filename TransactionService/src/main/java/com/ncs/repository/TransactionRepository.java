package com.ncs.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ncs.model.Transaction;
public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{
}
