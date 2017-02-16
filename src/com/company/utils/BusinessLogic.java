package com.company.utils;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by agrawald on 16/2/17.
 */
public interface BusinessLogic {
    Function<int[], Integer> CALCULATE_HASH = ints -> {
        int result = 0;
        for(int i: ints) {
            result += i;
        }
        return result;
    };

    BiFunction<int[], int[], Boolean> IS_EQUAL_LENGTH = (thisInts, thatInts) -> thisInts.length == thatInts.length;

    BiFunction<int[], int[], Boolean> IS_EQUAL_HASH =  (thisInts, thatInts) -> CALCULATE_HASH.apply(thisInts)
            .equals(CALCULATE_HASH.apply(thatInts));

    BiFunction<int[], int[], Boolean> IS_EQUAL_DIGITS = Arrays::equals;


}
