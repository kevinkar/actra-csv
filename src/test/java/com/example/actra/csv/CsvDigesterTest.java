package com.example.actra.csv;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvDigesterTest {

    @Test
    void testProvider01FI() {
        String sampleFile = "samples\\provder01-1-fi.csv";
        List<Transaction> transactions = readFile(sampleFile);
        // TODO: Actual test for contents read
        assertEquals(10, transactions.size());
    }

    @Test
    void testProvider01SV() {
        String sampleFile = "samples\\provder01-2-sv.csv";
        List<Transaction> transactions = readFile(sampleFile);
        // TODO: Actual test for contents read
        assertEquals(10, transactions.size());
    }

    @Test
    void testProvider02FI() {
        String sampleFile = "samples\\provider02-1-fi.csv";
        List<Transaction> transactions = readFile(sampleFile);
        // TODO: Actual test for contents read
        assertEquals(10, transactions.size());
    }

    @Test
    void testProvider02SV() {
        String sampleFile = "samples\\provider02-2-sv.csv";
        List<Transaction> transactions = readFile(sampleFile);
        // TODO: Actual test for contents read
        assertEquals(10, transactions.size());
    }

    @Test
    void testProvider02EN() {
        String sampleFile = "samples\\provider02-3-en.csv";
        List<Transaction> transactions = readFile(sampleFile);
        // TODO: Actual test for contents read
        assertEquals(10, transactions.size());
    }

    private List<Transaction> readFile(String sampleFile) {

        List<Transaction> transactions = new ArrayList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(sampleFile)) {
            CsvDigester.digest(is, transactions::add);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return transactions;

    }

}