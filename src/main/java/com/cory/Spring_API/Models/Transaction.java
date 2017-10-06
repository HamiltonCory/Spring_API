package com.cory.Spring_API.Models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @SequenceGenerator(name = "transactionSequence", sequenceName = "transactionId_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSequence")
    @Column(name = "transactionId")
    private int transactionId;

    @Column(name = "accountId")
    private int accountId;

    @Column(name = "transactionType")
    private String transactionType;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "amount")
    private Double amount;

    public Transaction() {
        setTimestamp();
    }

    public Transaction(Transaction transaction) {
        this.accountId = transaction.accountId;
        setTransactionType(transaction.getTransactionType());
        setTimestamp();
        this.amount = transaction.amount;
    }

    public Transaction(int accountId, String transactionType, Double amount) {
        this.accountId = accountId;
        setTransactionType(transactionType);
        setTimestamp();
        this.amount = amount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        String type= transactionType.trim().toUpperCase();
        if (type.equals("CREDIT") || type.equals("DEBIT")){
            this.transactionType = type;
        }
        else this.transactionType = "UNKNOWN";
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        this.timestamp = sdf.format(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
    }
    public void setTimestamp(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        this.timestamp = sdf.format(timestamp);
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (transactionId != that.transactionId) return false;
        if (accountId != that.accountId) return false;
        if (transactionType != null ? !transactionType.equals(that.transactionType) : that.transactionType != null)
            return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;
    }

    @Override
    public int hashCode() {
        int result = transactionId;
        result = 31 * result + accountId;
        result = 31 * result + (transactionType != null ? transactionType.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
       // SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", accountId=" + accountId +
                ", transactionType='" + transactionType + '\'' +
                ", timestamp=" + timestamp +
                ", amount=" + f.format(amount) +
                '}';
    }
}
