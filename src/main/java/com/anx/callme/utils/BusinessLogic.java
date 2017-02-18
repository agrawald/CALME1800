package com.anx.callme.utils;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by agrawald on 16/2/17.
 */
public interface BusinessLogic {
    /**
     * Function to calculate has for the number provided
     */
    Function<int[], Integer> CALCULATE_HASH = ints -> {
        int result = 0;
        for(int i: ints) {
            result += i;
        }
        return result;
    };

    /**
     * Function to check if the lengths of two numbers are same
     */
    BiFunction<int[], int[], Boolean> IS_EQUAL_LENGTH = (thisInts, thatInts) -> thisInts.length == thatInts.length;

    /**
     * Function to check if the hash of two numbers are equal
     */
    BiFunction<int[], int[], Boolean> IS_EQUAL_HASH =  (thisInts, thatInts) -> CALCULATE_HASH.apply(thisInts)
            .equals(CALCULATE_HASH.apply(thatInts));

    /**
     * Function to check if both the numbers have same digits
     */
    BiFunction<int[], int[], Boolean> IS_EQUAL_DIGITS = Arrays::equals;
}
