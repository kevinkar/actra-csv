package com.example.actra.csv.impl.providers;

import com.example.actra.csv.Transformer;
import com.example.actra.csv.impl.TransactionImpl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Transformer for Provider 01.
 */
class Provider01Transformer implements Transformer {

    private static final String EMPTY_FILLER = "-";
    private static final String EMPTY_FILLER_QUOTED = "'-'";
    private static final String EMPTY_STRING_QUOTES = "''";
    private final boolean lenient = false;

    @Override
    public TransactionImpl transform(List<String> row) {
        String bookingDate = row.get(0);
        String amount = row.get(2);
        String senderName = row.get(4);
        String recipientName = row.get(5);
        String message = row.get(9);

        LocalDate parsedDate = parseDate(bookingDate);
        BigDecimal parsedAmount = null;
        try {
            parsedAmount = parseAmount(amount);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String parsedSender = parseSender(senderName);
        String parsedRecipient = parseRecipient(recipientName);
        String parsedMessage = parseMessage(message);

        return new TransactionImpl(parsedDate, parsedAmount, parsedSender, parsedRecipient, parsedMessage);
    }

    public BigDecimal parseAmount(String amountStr) throws ParseException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        String pattern = "+#,##0.###;-#,##0.###";
        // Numbers start with + or - sign for positive and negative, respectively.
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(dateStr, formatter);
    }

    public String parseSender(String counterpart) {
        return EMPTY_FILLER.equals(counterpart) ? "SELF" : counterpart;
    }

    public String parseRecipient(String counterpart) {
        return EMPTY_FILLER.equals(counterpart) ? "SELF" : counterpart;
    }

    public String parseMessage(String msg) {
        if (EMPTY_FILLER_QUOTED.equals(msg) || msg == null) {
            return EMPTY_STRING_QUOTES;
        } else {
            return msg;
        }
    }
}
