package com.anx.callme.data;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to hold a section in a telephone seperated by a delimiter
 * This class will also hold the number of words for this sections
 *
 * Created by agrawald on 16/02/17.
 */
public class Section {
    int[] digits;
    @Getter private List<Word> words;

    /**
     * Constructor to set numbers as char array
     * @param number
     */
    public Section(int number) {
        this.words = new ArrayList<>();
        char[] chars = String.valueOf(number).toCharArray();
        this.digits = new int[chars.length];
        for(int i=0; i<chars.length; i++) {
            this.digits[i]=Character.getNumericValue(chars[i]);
        }
    }

    public int[] getDigits() {
        return digits;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public String getNumber() {
        String number = "";
        for(int i: digits) {
            number = number + i;
        }
        return number;
    }
}
