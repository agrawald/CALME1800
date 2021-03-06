package com.anx.callme.service;

import com.anx.callme.data.Section;
import com.anx.callme.data.Word;
import com.anx.callme.utils.BusinessLogic;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Service to provide functionality to search dictionary
 * Created by agrawald on 17/02/17.
 */
@Slf4j
public enum DictionarySvc {
    INSTANCE;

    /**
     * Using concurrent lined queue to hold suitably big dictionary in-memory.
     * Also we would be using parallel stream to search for the words hence it should be concurrent
     */
    static ConcurrentLinkedQueue<Word> DICTIONARY = new ConcurrentLinkedQueue<>();
    private static final String DEFAULT_DICTIONARY = "data/dictionary.txt";

    /**
     * Static function to initialize the dictionary queue
     *
     * @param dictionaryFilePath
     * @throws IOException
     */
    public void init( String dictionaryFilePath) throws IOException {
        if(dictionaryFilePath == null || dictionaryFilePath.isEmpty()) {
            dictionaryFilePath = DEFAULT_DICTIONARY;
            log.warn("Using Default Dictionary");
        }

        DICTIONARY = Files.readAllLines(Paths.get(dictionaryFilePath))
                .parallelStream()
                .map(line -> line.replaceAll("[^a-zA-Z ]", ""))
                .map(Word::new)
                .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));
    }

    /**
     * Function to search for all the suitable words for the phone number section provided
     *
     * @param section
     * @return List of words
     */
    public List<Word> search(final Section section) {
        return DICTIONARY.parallelStream()
                .filter(word -> BusinessLogic.IS_EQUAL_LENGTH.apply(word.getNumber(), section.getDigits()))
                .filter(word -> BusinessLogic.IS_EQUAL_HASH.apply(word.getNumber(), section.getDigits()))
                .filter(word -> BusinessLogic.IS_EQUAL_DIGITS.apply(word.getNumber(), section.getDigits()))
                .collect(Collectors.toList());
    }

}
