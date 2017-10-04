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

    @Column(name = "accountId")
    private int accountId;

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
        if (accountId != that.accountId) return false;
        if (transaction_type != null ? !transaction_type.equals(that.transaction_type) : that.transaction_type != null)
            return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;
    }

    @Override
    public int hashCode() {
        int result = transaction_id;
        result = 31 * result + accountId;
        result = 31 * result + (transaction_type != null ? transaction_type.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", accountId=" + accountId +
                ", transaction_type='" + transaction_type + '\'' +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                '}';
    }
}
