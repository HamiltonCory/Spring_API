package com.cory.Spring_API.Models;


import javax.persistence.*;
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

    public Account() {}

    public int getaccountId() {
        return accountId;
    }

    public double getPrincipal() {
        return principal;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (getaccountId() != account.getaccountId()) return false;
        if (Double.compare(account.getPrincipal(), getPrincipal()) != 0) return false;
        return getTransactions() != null ? getTransactions().equals(account.getTransactions()) : account.getTransactions() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getaccountId();
        temp = Double.doubleToLongBits(getPrincipal());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getTransactions() != null ? getTransactions().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", principal=" + principal +
                ", transactions=" + transactions +
                '}';
    }
}
