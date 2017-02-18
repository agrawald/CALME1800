package com.anx.callme.data

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by agrawald on 18/2/17.
 */
class WordTest extends Specification {
    def oWord;

    @Unroll
    def "should create a word for #word with encoding as #encode"(String word, int encoding) {
        setup:
        oWord = new Word(word);
        expect:
        oWord.getNumber().collect { "$it" }.join('') as int == encoding
        where:
        word   | encoding
        "call" | 2255
        "me"   | 63
    }

    def "should throw exception when word is empty"() {
        when:
        oWord = new Word("")
        then:
        thrown(NumberFormatException.class)
    }

    def "should throw exception when word is null"() {
        when:
        oWord = new Word(null)
        then:
        thrown(NullPointerException.class)
    }
}
