package com.cory.Spring_API.Service;

import com.cory.Spring_API.Models.Account;
import com.cory.Spring_API.Models.Transaction;
import com.cory.Spring_API.Repositories.AccountRepo;
import com.cory.Spring_API.Repositories.TransactionRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@Transactional
public class BusinessLogic {
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    public List<Account> retrieveAllAccounts() {
        List<Account> list = accountRepo.findAll();
        return list;
    }

    public Account retrieveAccount(int account_id) {
        Account account = accountRepo.findByAccount_Id(account_id);
        return account;
    }

    public List<Transaction> retrieveAllTransactions() {
        List<Transaction> list = transactionRepo.findAll();
        return list;
    }

    public List<Transaction> retrieveTransactions(int account_id) {
        List<Transaction> list = transactionRepo.findByAccount_Id(account_id);
        return list;
    }
}