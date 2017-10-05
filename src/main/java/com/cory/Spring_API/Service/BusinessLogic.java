package com.cory.Spring_API.Service;

import com.cory.Spring_API.Models.Account;
import com.cory.Spring_API.Models.Ledger;
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

    public Account retrieveAccount(int accountId) {
        Account account = accountRepo.findByAccountId(accountId);
        return account;
    }
    public String viewLedger(int accountId){
        Account account = accountRepo.findByAccountId(accountId);
        String ledger = account.viewLedger();
        return ledger;
    }

    public List<Transaction> retrieveAllTransactions() {
        List<Transaction> list = transactionRepo.findAll();
        return list;
    }

    public List<Transaction> retrieveTransactions(int accountId) {
        List<Transaction> list = transactionRepo.findByAccountId(accountId);
        return list;
    }

    public Account postAccount(Account account) {
       Account savedAccount = accountRepo.save(account);
       return savedAccount;
    }

    public Transaction postTransaction(Transaction transaction) {
        Transaction savedTransaction= transactionRepo.save(transaction);
        return savedTransaction;
    }

    public void updateLedger(Account account) {
        account.updateLedger();
    }
}