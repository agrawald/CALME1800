package com.anx.callme.service;

import com.anx.callme.data.PhoneNumber;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Service to convert the phone number to proper string.
 *
 * Created by agrawald on 17/02/17.
 */
@Slf4j
public class PhoneNumberConverterSvc {
    static List<PhoneNumber> PHONE_NUMBERS;
    private String inputFilePath;

    public PhoneNumberConverterSvc(String inputFilePath) throws IOException {
        this.inputFilePath = inputFilePath;
        this.init();
    }

    /**
     * Function to initialize the phone numbers
     * @throws IOException
     */
    public void init() throws IOException {
        //lets validate the file path if provided
        if (inputFilePath == null || inputFilePath.isEmpty()) {
            log.warn("Not input file provided. Falling back to console input");
            Scanner console = new Scanner(System.in);
            String phoneNumber = console.next();
            PHONE_NUMBERS = Collections.singletonList(new PhoneNumber(phoneNumber));
        } else {
            //lets read and load all the numbers in memory
            PHONE_NUMBERS = Files.readAllLines(Paths.get(inputFilePath))
                    .parallelStream()
                    .map(PhoneNumber::new)
                    .collect(Collectors.toList());
        }
    }

    /**
     * Function to convert
     */
    public void convert() {
        PHONE_NUMBERS.parallelStream()
                .map(PhoneNumber::getSections)
                .forEach(sections -> Arrays.asList(sections)
                        .parallelStream()
                        .forEach(section -> section.setWords(DictionarySvc.INSTANCE.search(section))));
    }

    /**
     * Function to display the phone numbers
     */
    public void display() {
        PHONE_NUMBERS.forEach(phoneNumber -> phoneNumber.print("", 0));
    }

}
