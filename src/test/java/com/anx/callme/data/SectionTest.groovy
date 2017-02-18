package com.anx.callme.data

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by agrawald on 18/2/17.
 */
class SectionTest extends Specification {
    def section

    @Unroll
    def "should create a section for #no with dogots #len"(int no, int len) {
        given:
        section = new Section(no)
        expect:
        section.digits.length == len
        where:
        no   | len
        2255 | 4
        63   | 2
        2    | 1
    }
}
