package com.company.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to define the encoding and load the encoding at the class loading time
 *
 * Created by agrawald on 16/02/17.
 */
public final class Encoding {
    private static Map<Character, Integer> NUMBER_PAD = new HashMap<>();

    static {
        NUMBER_PAD.put('A', 2);
        NUMBER_PAD.put('B', 2);
        NUMBER_PAD.put('C', 2);
        NUMBER_PAD.put('D', 3);
        NUMBER_PAD.put('E', 3);
        NUMBER_PAD.put('F', 3);
        NUMBER_PAD.put('G', 4);
        NUMBER_PAD.put('H', 4);
        NUMBER_PAD.put('I', 4);
        NUMBER_PAD.put('J', 5);
        NUMBER_PAD.put('K', 5);
        NUMBER_PAD.put('L', 5);
        NUMBER_PAD.put('M', 6);
        NUMBER_PAD.put('N', 6);
        NUMBER_PAD.put('O', 6);
        NUMBER_PAD.put('P', 7);
        NUMBER_PAD.put('Q', 7);
        NUMBER_PAD.put('R', 7);
        NUMBER_PAD.put('S', 7);
        NUMBER_PAD.put('T', 8);
        NUMBER_PAD.put('U', 8);
        NUMBER_PAD.put('V', 8);
        NUMBER_PAD.put('W', 9);
        NUMBER_PAD.put('X', 9);
        NUMBER_PAD.put('Y', 9);
        NUMBER_PAD.put('Z', 9);
    }

    public static int getNumber(char letter) {
        return NUMBER_PAD.get(Character.toUpperCase(letter));
    }
}