package com.example.actra.csv;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Transaction type. Given the typical data fields.
 */
public interface Transaction {

    LocalDate getBookingDate();

    BigDecimal getAmount();

    String getSender();

    String getRecipient();

    String getMessage();

}
