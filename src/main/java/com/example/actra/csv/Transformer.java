package com.example.actra.csv;

import java.util.List;

/**
 * Functional interface for a CSV Transformer.
 */
public interface Transformer {

    Transaction transform(List<String> row);

}
