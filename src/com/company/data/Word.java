package com.company.data;

import com.company.utils.NumberPad;
import com.company.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by agrawald on 16/02/17.
 */
public class Word {
    private int[] number;
    private String word;

    public int[] getNumber() {
        return number;
    }

    public Word(String word) {
        this.word = word;
        this.number = new int[word.length()];
        if (word.length() < 2) {
            this.number[0] = Integer.parseInt(word);
        } else {
            for (int i = 0; i < word.length(); i++) {
                number[i] = NumberPad.getNumber(word.charAt(i));
            }
        }
    }

    @Override
    public String toString() {
        return word.toUpperCase();
    }
}
