package com.anx.callme.utils

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by agrawald on 18/2/17.
 */
class BusinessLogicTest extends Specification {
    @Unroll
    def "should calculate hash #hash for #numbers"(int[] numbers, int hash) {
        expect:
        BusinessLogic.CALCULATE_HASH.apply(numbers) == hash
        where:
        numbers   | hash
        [1, 2, 3] | 6
        [1, 1, 1] | 3
        []        | 0
    }

    @Unroll
    def "should check #no1 and #no2 have equal length #result"(int[] no1, int[] no2, boolean result) {
        expect:
        BusinessLogic.IS_EQUAL_LENGTH.apply(no1, no2).booleanValue() == result
        where:
        no1       | no2       || result
        [1, 2, 3] | [3, 4, 5] || true
        [1, 2]    | [3, 4, 5] || false
        []        | []        || true
    }

    @Unroll
    def "should check #no1 and #no2 have equal hash #result"(int[] no1, int[] no2, boolean result) {
        expect:
        BusinessLogic.IS_EQUAL_HASH.apply(no1, no2).booleanValue() == result
        where:
        no1       | no2       || result
        [1, 2, 3] | [3, 4, 5] || false
        [1, 2, 3] | [3, 2, 1] || true
        [2, 3]    | [1, 2, 2] || true
    }

    @Unroll
    def "should check #no1 and #no2 have equal digits #result"(int[] no1, int[] no2, boolean result) {
        expect:
        BusinessLogic.IS_EQUAL_DIGITS.apply(no1, no2).booleanValue() == result
        where:
        no1       | no2       || result
        [1, 2, 3] | [1, 2, 3] || true
        [1, 2]    | [3, 4, 5] || false
        []        | []        || true
    }
}
