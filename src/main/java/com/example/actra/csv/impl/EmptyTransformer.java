package com.example.actra.csv.impl;

import com.example.actra.csv.Transformer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

class EmptyTransformer implements Transformer {

    @Override
    public TransactionImpl transform(List<String> row) {
        return new TransactionImpl(LocalDate.ofEpochDay(0), BigDecimal.ZERO, "", "", "");
    }

}
