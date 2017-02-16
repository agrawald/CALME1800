package com.company.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by agrawald on 16/02/17.
 */
public class TelephoneNumber {
    private static final Pattern TELEPHONE_PATTERN = Pattern.compile("^([0-9]{1}?)[^0-9]?([0-9]{3}?)[^0-9]?([0-9]{4}?)[^0-9]?([0-9]{2}?)");
    private Word[] sections;
    private String number;

    public TelephoneNumber(String number) {
        this.number = number;
        Matcher matcher = TELEPHONE_PATTERN.matcher(number);
        if(matcher.matches()) {
            sections = new Word[matcher.groupCount()];
            for(int i=0; i<matcher.groupCount(); i++) {
                sections[i]= new Word(Integer.parseInt(matcher.group()));
            }
        }
    }

    public Word[] getSections() {
        return sections;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(number);
        for(Word section: sections) {
            sb.append("\n")
                    .append(section.toString());
        }
        return sb.toString();
    }
}
