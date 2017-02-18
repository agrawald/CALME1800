package com.anx.callme.utils

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by agrawald on 18/2/17.
 */
class EncodingTest extends Specification {
    @Unroll
    def "should get number #no for letter #character"(char character, int no) {
        expect:
        Encoding.getNumber(character) == no
        where:
        character | no
        'A'    | 2
        'a'    | 2
        'b'    | 2
        'G'    | 4
    }

    def "should throw exception for blank"() {
        when:
        Encoding.getNumber('' as char)
        then:
        thrown(StringIndexOutOfBoundsException.class)
    }

    def "should throw exception for null"() {
        when:
        Encoding.getNumber(null as char)
        then:
        thrown(NullPointerException.class)
    }
}
