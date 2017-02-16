package com.company.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by agrawald on 16/02/17.
 */
public class Section {
    int[] digits;
    List<Word> words;

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
