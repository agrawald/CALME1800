
package com.company;

import com.company.data.Section;
import com.company.data.PhoneNumber;
import com.company.data.Word;
import com.company.utils.Utils;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Main {
    private static ConcurrentLinkedQueue<Word> DICTIONARY = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws IOException {
        //TODO read file from CMD
        String dictionaryFilePath = "data/dictionary.txt";
        uploadDictionary(dictionaryFilePath);
        long start = System.currentTimeMillis();

        List<PhoneNumber> phoneNumberList = Files.readAllLines(Paths.get("data/number.txt"))
                .parallelStream()
                .map(PhoneNumber::new)
                .collect(Collectors.toList());

        phoneNumberList.parallelStream()
                .map(PhoneNumber::getSections)
                .forEach(sections -> Arrays.asList(sections)
                        .parallelStream()
                        .forEach(section -> section.setWords(DICTIONARY.parallelStream()
                                .filter(word -> Utils.IS_EQUAL_LENGTH.apply(word.getNumber(), section.getDigits()))
                                .filter(word -> Utils.IS_EQUAL_HASH.apply(word.getNumber(), section.getDigits()))
                                .filter(word -> Utils.IS_EQUAL_DIGITS.apply(word.getNumber(), section.getDigits()))
                                .collect(Collectors.toList()))));
        phoneNumberList.forEach(phoneNumber -> {
            System.out.println("Number: " + phoneNumber.getNumber());
            phoneNumber.print("", 0);
        });

        long end = System.currentTimeMillis();
        System.out.println(" Total Time: " + (end - start) + "ms");
//        System.out.println("Number: " + phoneNumber.getNumber());
//        phoneNumber.print("", 0);
    }

    private static void uploadDictionary(String dictionaryFilePath) throws IOException {
        DICTIONARY = Files.readAllLines(Paths.get(dictionaryFilePath))
                .parallelStream()
                .map(line -> line.replaceAll("[^a-zA-Z ]", ""))
                .map(Word::new)
                .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));
    }
}
