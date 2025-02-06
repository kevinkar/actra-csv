package com.example.actra.csv;

import com.example.actra.csv.impl.ProviderSelector;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.io.input.BOMInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.Consumer;

public class CsvDigester {

    private static final char SEMICOLON = ';';
    private static final char QUOTE_SINGLE = '\'';

    public static void digest(InputStream inputStream, Consumer<Transaction> consumer) throws DigesterException {
        if (inputStream == null) {
            // This to avoid NPE when accessing the stream
            throw new DigesterException("Empty content stream detected.");
        }
        // Wrap the input stream in a BOMInputStream to get rid of the BOM
        try (CSVReader reader = new CSVReaderBuilder(
                new InputStreamReader(BOMInputStream.builder().setInputStream(inputStream).get(), StandardCharsets.UTF_8))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(SEMICOLON)
                        .withQuoteChar(QUOTE_SINGLE).build())
                .build()) {

            ProviderSelector selector = new ProviderSelector();
            String[] row = reader.readNext();
            if (row == null) {
                throw new DigesterException("Empty contents detected.");
            }
            Provider provider = selector.resolveProvider(row);
            if (provider == null) {
                throw new DigesterException("Unable to find a suitable provider for the contents.");
            }

            Transformer transformer = provider.getCsvTransformer();
            if (transformer == null) {
                throw new DigesterException("The provider does not supply a transformer for the contents.");
            }

            while ((row = reader.readNext()) != null) {
                Transaction tr = transformer.transform(Arrays.asList(row));
                consumer.accept(tr);
            }

        } catch (IOException | CsvException e) {
            throw new DigesterException("Digester failed with external exception.", e);
        }

    }

}
