package com.example.actra.csv;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvTest {

    public static void main(String[] args) {

        String samples = "src\\main\\resources\\samples\\";

        String fileName1 = "test01.csv";
        String fileName2 = "test02.csv";

        Path currentDirectory = Paths.get("").toAbsolutePath();
        System.out.println("Current Directory: " + currentDirectory);

        Path samplesPath = currentDirectory.resolve(samples).normalize();
        Path filePath1 = samplesPath.resolve(fileName1);
        Path filePath2 = samplesPath.resolve(fileName2);

        try (InputStream is = Files.newInputStream(filePath1)) {

            CsvDigester.digest(is, System.out::println);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (InputStream is = Files.newInputStream(filePath2)) {

            CsvDigester.digest(is, System.out::println);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
