
package com.company;

import com.company.data.TelephoneNumber;
import com.company.data.Word;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class Main {
    private static ConcurrentLinkedQueue<Word> DICTIONARY = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws IOException {
        //TODO read file from CMD
        String dictionaryFilePath = "data/dictionary.txt";
        uploadDictionary(dictionaryFilePath);
        long start = System.currentTimeMillis();
        TelephoneNumber telephoneNumber = new TelephoneNumber("2255.63");
        for (Word section : telephoneNumber.getSections()) {
            if (section.getLength() > 1) {
                DICTIONARY.parallelStream()
                        .filter(word -> word.getLength() == section.getLength())
                        .filter(word -> word.hashCode() == section.hashCode())
                        .filter(word -> word.equals(section))
                        .map(word -> word.get)
                        .collect(Collectors.toList());
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(telephoneNumber);
        System.out.println(" Total Time: " + (end - start) + "ms");
    }

    private static void uploadDictionary(String dictionaryFilePath) throws IOException {
        DICTIONARY = Files.readAllLines(Paths.get(dictionaryFilePath))
                .parallelStream()
                .map(line -> line.replaceAll("[^a-zA-Z ]", ""))
                .map(Word::new)
                .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));
    }
}
