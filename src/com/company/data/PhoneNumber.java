package com.company.data;

/**
 * Created by agrawald on 16/02/17.
 */
public class PhoneNumber {
    private Section[] sections;
    private String number;

    public PhoneNumber(String number) {
        this.number = number;
        String[] tokens = number.split("[^0-9]");
        sections = new Section[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            sections[i] = new Section(Integer.parseInt(tokens[i]));
        }
    }

    public String getNumber() {
        return number;
    }

    public Section[] getSections() {
        return sections;
    }

    public void print(String sb, int count) {
        if (count >= sections.length) {
            System.out.println("\t" + sb);
        } else {
            if (count < sections.length && count > 0) sb = sb + "-";
            Section section = sections[count];
            if (section.words == null || section.words.size() == 0) {
                String sb2 = sb + section.getNumber();
                print(sb2, ++count);
            }
            for (Word word : section.words) {
                String sb2 = sb + word.toString();
                print(sb2, ++count);
                count--;
            }
        }
    }
}
