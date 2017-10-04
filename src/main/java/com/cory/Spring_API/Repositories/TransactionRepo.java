package com.cory.Spring_API.Repositories;

import com.cory.Spring_API.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByAccountId(int accountId);
}
