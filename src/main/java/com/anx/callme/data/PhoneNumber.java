package com.anx.callme.data;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Class to parse the phone number parse it and seperate it in sections
 *
 * Created by agrawald on 16/02/17.
 */
@Slf4j
@Getter
public class PhoneNumber {
    Section[] sections;

    /**
     * Constructor to parse the number into sections
     * @param number
     */
    public PhoneNumber(String number) {
        String[] tokens = number.split("[^0-9]");
        sections = new Section[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            sections[i] = new Section(Integer.parseInt(tokens[i]));
        }
    }

    public Section[] getSections() {
        return sections;
    }

    /**
     * Function to print all the possible combinations of the words.
     * @param sb
     * @param count
     */
    public void print(String sb, int count) {
        if (count >= sections.length) {
            log.info("\t" + sb);
        } else {
            if (count < sections.length && count > 0) sb = sb + "-";
            Section section = sections[count];
            if (section.getWords() == null || section.getWords().size() == 0) {
                String sb2 = sb + section.getNumber();
                print(sb2, ++count);
            }
            for (Word word : section.getWords()) {
                String sb2 = sb + word.toString();
                print(sb2, ++count);
                count--;
            }
        }
    }
}
