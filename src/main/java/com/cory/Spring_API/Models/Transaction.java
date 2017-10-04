package com.cory.Spring_API.Models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @SequenceGenerator(name = "transactionSequence", sequenceName = "transaction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSequence")
    @Column(name = "transaction_id")
    private int transaction_id;

    @Column(name = "account_id")
    private int account_id;

    @Column(name = "transaction_type")
    private String transaction_type;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "amount")
    private Double amount;

    public Transaction(Transaction newTransaction) {
        this.transaction_type = newTransaction.transaction_type;
        this.timestamp = newTransaction.timestamp;
        this.amount = newTransaction.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (transaction_id != that.transaction_id) return false;
        if (!transaction_type.equals(that.transaction_type)) return false;
        if (!timestamp.equals(that.timestamp)) return false;
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = transaction_id;
        result = 31 * result + transaction_type.hashCode();
        result = 31 * result + timestamp.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", transaction_type='" + transaction_type + '\'' +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                '}';
    }
}
