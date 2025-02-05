package com.example.actra.csv;

import java.util.Collections;
import java.util.List;

/**
 * Providers are the banks providing the CSVs
 */
public interface Provider {

    /**
     * The headers the provider provides.
     *
     * @return Headers associated with the provider.
     */
    static List<String[]> getHeaders() {
        return Collections.emptyList();
    }

    /**
     * A CSV transformer for CSVs for the provider.
     *
     * @return CSV transformer for the provider
     */
    Transformer getCsvTransformer();

    /**
     * Simple getter
     *
     * @return The name of the provider
     */
    String getName();

}
