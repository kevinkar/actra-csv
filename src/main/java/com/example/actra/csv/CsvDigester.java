package com.example.actra.csv;

import com.example.actra.csv.impl.providers.ProviderSelector;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.io.input.BOMInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CsvDigester {

    private static final char SEMICOLON = ';';
    private static final char QUOTE_SINGLE = '\'';

    public static void digest(InputStream inputStream, Consumer<Transaction> consumer) {

        // Wrap the input stream in a BOMInputStream to get rid of the BOM
        try (CSVReader reader = new CSVReaderBuilder(
                new InputStreamReader(BOMInputStream.builder().setInputStream(inputStream).get(), StandardCharsets.UTF_8))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(SEMICOLON)
                        .withQuoteChar(QUOTE_SINGLE).build())
                .build()) {

            ProviderSelector selector = new ProviderSelector();

            String[] row = reader.readNext();

            Provider provider = selector.resolveProvider(row);

            if (provider == null) {
                System.out.println("Unable to find a provider match for the file contents.");
                System.exit(1);
                return;
            }

            List<Transaction> transactions = new ArrayList<>();
            Transformer transformer = provider.getCsvTransformer();

            while ((row = reader.readNext()) != null) {
                Transaction tr = transformer.transform(Arrays.asList(row));
                transactions.add(tr);
            }

            for (Transaction tr : transactions) {
                consumer.accept(tr);
            }


        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

    }

}
