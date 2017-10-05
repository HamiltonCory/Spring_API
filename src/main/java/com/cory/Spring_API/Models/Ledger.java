package com.cory.Spring_API.Models;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

@Component
public class Ledger {

    private List<Double> debits;
    private List<Double> credits;

    public Ledger() {
        this.debits = new ArrayList<Double>();
        this.credits = new ArrayList<Double>();
    }
    public Ledger(List<Transaction> transactions) {
        this();
        updateLedger(transactions);
    }

    public List<Double> getDebits() {
        return debits;
    }

    public void setDebits(List<Double> debits) {
        this.debits = debits;
    }

    public List<Double> getCredits() {
        return credits;
    }

    public void setCredits(List<Double> credits) {
        this.credits = credits;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ledger ledger = (Ledger) o;

        if (getDebits() != null ? !getDebits().equals(ledger.getDebits()) : ledger.getDebits() != null) return false;
        return getCredits() != null ? getCredits().equals(ledger.getCredits()) : ledger.getCredits() == null;
    }

    @Override
    public int hashCode() {
        int result = getDebits() != null ? getDebits().hashCode() : 0;
        result = 31 * result + (getCredits() != null ? getCredits().hashCode() : 0);
        return result;
    }

    public void updateLedger(List<Transaction> transactions) {
        if (transactions != null && transactions.size() > 0) {
            int length = transactions.size();
            this.debits = new ArrayList<Double>();
            this.credits = new ArrayList<Double>();

            for (int x = 0; x < length; x++) {
                Transaction t = transactions.get(x);
                String transactionType = t.getTransactionType();
                Double amount = t.getAmount();
                if (transactionType.toUpperCase().equals("DEBIT")) {
                    //debits.add(x, amount);
                    debits.add(x, amount);
                    credits.add(x, 0.00);
                } else if (transactionType.toUpperCase().equals("CREDIT")) {
                    //credits.add(x, amount);
                    debits.add(x, 0.00);
                    credits.add(x, amount);
                } else {
                    System.out.print("This transaction was not a debit or a credit. Please review transaction: " + t.getTransactionId());
                }
            }
        } else {
            System.out.println("No transactions found!");
        }
        System.out.println(debits + "\n" + credits);
    }

    public String toString() {
        String indent = "        ";

        String debitOutput = "cash-out:\ndebit   | credit\n--------+--------\n";
        for (int x = 0; x < debits.size(); x++) {
            Double charge = debits.get(x);
            debitOutput += charge + indent.substring(0, indent.length() - Double.toString(charge).length()) + "|\n";
        }
        String creditOutput = "principal:\ndebit   | credit\n--------+--------\n";
        for (int x = 0; x < credits.size(); x++) {
            Double charge = credits.get(x);
            creditOutput += "        | " + charge + "\n";
        }
        return debitOutput + "\n" + creditOutput;
    }
}

