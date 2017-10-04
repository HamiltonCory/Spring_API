package com.cory.Spring_API.Models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @SequenceGenerator(name = "accountSequence", sequenceName = "account_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSequence")
    @Column(name = "account_id")
    private int account_id;

    @Column(name = "principal")
    private double principal;

    @OneToMany(mappedBy = "account_id" , fetch = FetchType.EAGER)
    private List<Transaction> transactions;

    public Account() {}

    public int getAccount_id() {
        return account_id;
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

        if (getAccount_id() != account.getAccount_id()) return false;
        if (Double.compare(account.getPrincipal(), getPrincipal()) != 0) return false;
        return getTransactions() != null ? getTransactions().equals(account.getTransactions()) : account.getTransactions() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getAccount_id();
        temp = Double.doubleToLongBits(getPrincipal());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getTransactions() != null ? getTransactions().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", principal=" + principal +
                ", transactions=" + transactions +
                '}';
    }
}
