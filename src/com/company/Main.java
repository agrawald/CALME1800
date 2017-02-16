
package com.company;

import com.company.service.DictionarySvc;
import com.company.service.PhoneNumberConverterSvc;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        DictionarySvc.INSTANCE.init("data/dictionary.txt");
        PhoneNumberConverterSvc.INSTANCE.init("data/number.txt");
        PhoneNumberConverterSvc.INSTANCE.convert();
        PhoneNumberConverterSvc.INSTANCE.display();
        long end = System.currentTimeMillis();
        System.out.println(" Total Time: " + (end - start) + "ms");
    }
}
