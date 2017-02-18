package com.anx.callme.service

import com.anx.callme.data.Section
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by agrawald on 18/2/17.
 */
class DictionarySvcTest extends Specification {

    def "should initialize the dictionary"() {
        when:
        DictionarySvc.INSTANCE.init("src/test/resources/dictionary.txt")
        then:
        DictionarySvc.DICTIONARY.size() == 4
    }

    @Unroll
    def "should be able to search #word at #id for number #no"(int no, int id, String word) {
        given:
        DictionarySvc.INSTANCE.init("src/test/resources/dictionary.txt")
        expect:
        DictionarySvc.INSTANCE.search(new Section(no)).get(id).toString() == word
        where:
        no   | id || word
        2255 | 0  || "CALL"
        2255 | 1  || "BALK"
        63   | 0  || "ME"
    }
}
