package com.example.actra.csv.impl;

import com.example.actra.csv.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

class TransactionImpl implements Transaction {

    private LocalDate bookingDate;
    private BigDecimal amount;
    private String sender;
    private String recipient;
    private String message;

    public TransactionImpl(LocalDate bookingDate, BigDecimal amount, String sender, String recipient, String message) {
        this.bookingDate = bookingDate;
        this.amount = amount;
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }

    public static TransactionImpl emptyTransaction() {
        return new TransactionImpl(LocalDate.ofEpochDay(0), BigDecimal.ZERO, "", "", "");
    }

    @Override
    public String toString() {
        return String.join(";", bookingDate.toString(), amount.toString(), sender, recipient, message);
    }

    public String toVerboseString() {
        return "Transaction: [\n"
                + "\tDate: " + bookingDate + "\n"
                + "\tAmount: " + amount + "\n"
                + "\tSender: " + sender + "\n"
                + "\tRecipient: " + recipient + "\n"
                + "\tMessage: " + message + "\n"
                + "]";
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
