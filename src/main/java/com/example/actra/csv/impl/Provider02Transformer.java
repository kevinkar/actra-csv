package com.example.actra.csv.impl;

import com.example.actra.csv.Transformer;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Transformer for Provider 02.
 */
class Provider02Transformer implements Transformer {

    private static final String EMPTY_STRING_QUOTES = "''";
    private static final String KEYWORD_SELF = "SELF";
    private static final String KEYWORD_UNKNOWN = "UNKNOWN";

    private final boolean lenient = false;

    @Override
    public TransactionImpl transform(List<String> row) {
        String bookingDate = row.get(0);
        String amount = row.get(1);
        String senderAccount = row.get(2);
        String name = row.get(4);
        String title = row.get(5);

        LocalDate parsedDate = parseDate(bookingDate);
        BigDecimal parsedAmount = null;
        try {
            parsedAmount = parseAmount(amount);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String parsedSender = resolveSender(senderAccount, name, title);
        String parsedRecipient = resolveRecipient(senderAccount, name, title);

        return new TransactionImpl(parsedDate, parsedAmount, parsedSender, parsedRecipient, EMPTY_STRING_QUOTES);
    }

    public static String resolveSender(String senderAccount, String name, String title) {
        if (senderAccount != null && !senderAccount.isEmpty()) {
            return KEYWORD_SELF;
        }
        String counterpart = (name == null || name.isEmpty()) ? title : name;
        return (counterpart == null || counterpart.isEmpty()) ? KEYWORD_UNKNOWN : counterpart;
    }

    public static String resolveRecipient(String senderAccount, String name, String title) {
        if (senderAccount == null || senderAccount.isEmpty()) {
            return KEYWORD_SELF;
        }
        String counterpart = (name == null || name.isEmpty()) ? title : name;
        return (counterpart == null || counterpart.isEmpty()) ? KEYWORD_UNKNOWN : counterpart;
    }

    public BigDecimal parseAmount(String amountStr) throws ParseException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        // Negative numbers start with (-)-sign, or none for positive numbers.
        String pattern = "#,##0.###;-#,##0.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        try {
            BigDecimal amount = (BigDecimal) decimalFormat.parse(amountStr);
            return amount;
        } catch (ParseException e) {
            if (lenient) {
                return BigDecimal.ZERO;
            } else {
                throw e;
            }
        }
    }

    public LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(dateStr, formatter);
    }

}
