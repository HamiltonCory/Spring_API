package com.cory.Spring_API.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.text.DecimalFormat;
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
    @JsonInclude
    private Ledger ledger;

    public Account() {
        this.ledger = new Ledger(transactions);
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

    public Ledger getLedger() {
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = new Ledger(transactions);
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

    public String viewLedger(){
        return ledger.toString();
    }

    public void updateLedger() {
        Double sum=0.00;
        for (Transaction t:transactions){
            String type= t.getTransactionType().toUpperCase();
            if(type.equals("DEBIT")){
                sum+=t.getAmount();
            }
            else if (type.equals("CREDIT")){
                sum-=t.getAmount();
            }
        }
        this.principal=sum;

        ledger.updateLedger(transactions);
    }
}
