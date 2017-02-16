package com.company.service;

import com.company.data.PhoneNumber;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by agrawald on 17/02/17.
 */
public enum PhoneNumberConverterSvc {
    INSTANCE;

    private static List<PhoneNumber> PHONE_NUMBERS;

    public void init(final String numberFilePath) throws IOException {
        PHONE_NUMBERS = Files.readAllLines(Paths.get(numberFilePath))
                .parallelStream()
                .map(PhoneNumber::new)
                .collect(Collectors.toList());
    }

    public void convert() {
        PHONE_NUMBERS.parallelStream()
                .map(PhoneNumber::getSections)
                .forEach(sections -> Arrays.asList(sections)
                        .parallelStream()
                        .forEach(section -> section.setWords(DictionarySvc.INSTANCE.search(section))));
    }

    public void display() {
        PHONE_NUMBERS.forEach(phoneNumber -> {
            System.out.println("Number: " + phoneNumber.getNumber());
            phoneNumber.print("", 0);
        });

    }

}
