package com.anx.callme.service

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by agrawald on 18/2/17.
 */
class PhoneNumberConverterSvcTest extends Specification {
    def phoneNumberConverterSvc

    void setup() {
        phoneNumberConverterSvc = new PhoneNumberConverterSvc("src/test/resources/number.txt")
    }

    def "should initialize the phone numbers"() {
        when:
        phoneNumberConverterSvc = new PhoneNumberConverterSvc("src/test/resources/number.txt")

        then:
        PhoneNumberConverterSvc.PHONE_NUMBERS.size() == 4

    }

    @Unroll
    def "should convert the phone numbers to words #word"(int noIdx, int sectionIdx, String word) {
        setup:
        DictionarySvc.INSTANCE.init("src/test/resources/dictionary.txt")
        phoneNumberConverterSvc.convert()
        expect:
        PhoneNumberConverterSvc.PHONE_NUMBERS.get(noIdx).getSections()[sectionIdx].getWords()[0].toString() == word
        where:
        noIdx | sectionIdx || word
        0     | 0          || "CALL"
        0     | 1          || "ME"
        1     | 0          || "CALL"
        2     | 0          || "ME"
        3     | 0          || "null"
    }
}
