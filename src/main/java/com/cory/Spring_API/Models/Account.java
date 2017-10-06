package com.cory.Spring_API.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @SequenceGenerator(name = "accountSequence", sequenceName = "accountId_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSequence")

    @JoinColumn(name="accountId")
    private int accountId;

    @Column(name = "principal")
    private double principal;

    @OneToMany(mappedBy = "accountId" , fetch = FetchType.EAGER)
    private List<Transaction> transactions;

    @Transient
    List<Double> debits;
    @Transient
    List<Double> credits;

    public Account() {
        this.debits = new ArrayList<Double>();
        this.credits = new ArrayList<Double>();
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (getAccountId() != account.getAccountId()) return false;
        if (Double.compare(account.getPrincipal(), getPrincipal()) != 0) return false;
        return getTransactions() != null ? getTransactions().equals(account.getTransactions()) : account.getTransactions() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getAccountId();
        temp = Double.doubleToLongBits(getPrincipal());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getTransactions() != null ? getTransactions().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
        return "Account{" +
                "accountId=" + accountId +
                ", principal=" + f.format(principal) +
                ", transactions=" + transactions +
                '}';
    }

    public void update() {
        Double sum=0.00;
        for (Transaction t:transactions){
            String type= t.getTransactionType().toUpperCase();
            if(type.equals("DEBIT")){
                sum-=t.getAmount();
            }
            else if (type.equals("CREDIT")){
                sum+=t.getAmount();
            }
        }
        this.principal=sum;
    }


    public void updateLedger() {
//        List<Double> debits = new ArrayList<Double>();
//        List<Double> credits = new ArrayList<Double>();
        if (transactions != null && transactions.size() > 0) {
            int length = transactions.size();
            debits = new ArrayList<Double>();
            credits = new ArrayList<Double>();

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

    public String viewLedger() {
//        List<Double> debits = new ArrayList<Double>();
//        List<Double> credits = new ArrayList<Double>();

        updateLedger();

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
        String output=debitOutput + "\n" + creditOutput;
        System.out.println(output);
        return output;
    }
}
