package com.anx.callme.data;

import com.anx.callme.utils.Encoding;

/**
 * Class to hold the words and its number representation
 *
 * Created by agrawald on 16/02/17.
 */
public class Word {
    private int[] number;
    private String word;

    public int[] getNumber() {
        return number;
    }

    /**
     * Constructor to set word and assign number to each letter
     * @param word
     */
    public Word(String word) {
        this.word = word;
        this.number = new int[word.length()];
        if (word.length() < 2) {
            this.number[0] = Integer.parseInt(word);
        } else {
            for (int i = 0; i < word.length(); i++) {
                number[i] = Encoding.getNumber(word.charAt(i));
            }
        }
    }

    @Override
    public String toString() {
        return word.toUpperCase();
    }
}
