package com.anx.callme.data

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by agrawald on 18/2/17.
 */
class PhoneNumberTest extends Specification {

    def phoneNumber;

    void cleanup() {

    }


    @Unroll
    def "should create a phone number object for #no with #length sections"(String no, int length) {
        given:
        phoneNumber = new PhoneNumber(no)
        expect:
        phoneNumber.sections.length == length
        where:
        no        | length
        "2255-63" | 2
        "225363"  | 1
    }

    def "should throw exception with a blank phone number"() {
        when:
        phoneNumber = new PhoneNumber("")

        then:
        thrown(NumberFormatException.class)
    }

    def "should throw exception with a null phone number"() {
        when:
        phoneNumber = new PhoneNumber(null)

        then:
        thrown(NullPointerException.class)
    }
}
