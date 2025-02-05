package com.example.actra.csv;

import java.util.List;

/**
 * Functional interface for a CSV Transformer.
 */
public interface Transformer {

    String EMPTY_STRING_QUOTES = "''";

    Transaction transform(List<String> row);

}
