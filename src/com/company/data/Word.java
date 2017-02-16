package com.company.data;

import com.company.utils.NumberPad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by agrawald on 16/02/17.
 */
public class Word{
    private int[] number;
    private List<String> words;

    public int getLength() {
        if(number != null) {
            return number.length;
        }
        return 0;
    }

    public Word(String word) {
        if(words == null) {
            words = new ArrayList<>();
        }
        this.words.add(word);
        this.number = new int[word.length()];
        for (int i = 0; i < word.length(); i++) {
            number[i] = NumberPad.getNumber(word.charAt(i));
        }
    }

    public Word(int number) {
        char[] chars = String.valueOf(number).toCharArray();
        this.number = new int[chars.length];
        for(int i=0; i<chars.length; i++) {
            this.number[i]=Character.getNumericValue(chars[i]);
        }

        if(this.number.length < 2) {
            this.words.add(String.valueOf(this.number));
        }
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        return Arrays.equals(number, word1.number);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i : number) {
            hash += i;
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(Arrays.toString(number));
        sb.append(" -> ");
        for(String word: words) {
            sb.append(word).append(" ");
        }
        return sb.toString();
    }
}
