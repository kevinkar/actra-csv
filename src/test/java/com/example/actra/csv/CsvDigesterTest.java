package com.example.actra.csv;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvDigesterTest {

    @Test
    void testProvider01FI() {
        String sampleFile = "samples\\provder01-1-fi.csv";
        List<Transaction> transactions = null;
        try {
            transactions = readFile(sampleFile);
        } catch (DigesterException e) {
            fail("Failed with " + e.getMessage());
        }
        // TODO: Actual test for contents read
        assertEquals(10, transactions.size());
    }

    @Test
    void testProvider01SV() {
        String sampleFile = "samples\\provder01-2-sv.csv";
        List<Transaction> transactions = null;
        try {
            transactions = readFile(sampleFile);
        } catch (DigesterException e) {
            fail("Failed with " + e.getMessage());
        }
        // TODO: Actual test for contents read
        assertEquals(10, transactions.size());
    }

    @Test
    void testProvider02FI() {
        String sampleFile = "samples\\provider02-1-fi.csv";
        List<Transaction> transactions = null;
        try {
            transactions = readFile(sampleFile);
        } catch (DigesterException e) {
            fail("Failed with " + e.getMessage());
        }
        // TODO: Actual test for contents read
        assertEquals(10, transactions.size());
    }

    @Test
    void testProvider02SV() {
        String sampleFile = "samples\\provider02-2-sv.csv";
        List<Transaction> transactions = null;
        try {
            transactions = readFile(sampleFile);
        } catch (DigesterException e) {
            fail("Failed with " + e.getMessage());
        }
        // TODO: Actual test for contents read
        assertEquals(10, transactions.size());
    }

    @Test
    void testProvider02EN() {
        String sampleFile = "samples\\provider02-3-en.csv";
        List<Transaction> transactions = null;
        try {
            transactions = readFile(sampleFile);
        } catch (DigesterException e) {
            fail("Failed with " + e.getMessage());
        }
        // TODO: Actual test for contents read
        assertEquals(10, transactions.size());
    }

    @Test
    void testEmpty() {
        String sampleFile = "samples\\empty.csv";
        assertThrows(DigesterException.class, () -> readFile(sampleFile));
    }

    @Test
    void testInvalid() {
        String sampleFile = "samples\\invalid.csv";
        assertThrows(DigesterException.class, () -> readFile(sampleFile));
    }

    @Test
    void testEmptyStream() {
        String sampleFile = "samples\\notExists.csv";
        List<Transaction> transactions = null;
        try {
            transactions = readFile(sampleFile);
        } catch (DigesterException e) {
            assertEquals("Empty content stream detected.", e.getMessage());
            return; // Above assertion passes the test
        }
        fail("The test should have failed before this as the file does not exist: " + sampleFile);
    }

    private List<Transaction> readFile(String sampleFile) throws DigesterException {

        List<Transaction> transactions = new ArrayList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(sampleFile)) {
            CsvDigester.digest(is, transactions::add);
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException in test for: " + sampleFile);
        } catch (IOException e) {
            fail("IOException in test for " + sampleFile);
        }
        return transactions;

    }

}