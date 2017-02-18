
package com.anx.callme;

import com.anx.callme.service.DictionarySvc;
import com.anx.callme.service.ValidatorSvc;
import com.anx.callme.service.PhoneNumberConverterSvc;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Where it all starts.
 */
@Slf4j
public class Application {
    private static final String OPT_DICTIONARY = "-d";

    public static void main(String[] args) throws IOException {
        String inputFilePath = null;
        String dictionaryFilePath = null;
        //lets check if user want to do anything special
        if (args != null) {
            switch (args.length) {
                case 0:
                    break;
                case 1:
                case 2:
                    inputFilePath = parseInputFilePath(args);
                    break;
                case 3:
                    inputFilePath = parseInputFilePath(args);
                    dictionaryFilePath = parseDictionaryFilePath(args);
                    break;
                default:
                    printUsage();
                    break;
            }
        }

        //lets start dancing
        long start = System.currentTimeMillis();
        DictionarySvc.INSTANCE.init(dictionaryFilePath);
        PhoneNumberConverterSvc phoneNumberConverterSvc = new PhoneNumberConverterSvc(inputFilePath);
        phoneNumberConverterSvc.convert();
        phoneNumberConverterSvc.display();
        long end = System.currentTimeMillis();
        log.info(" Total Time: {}ms", (end - start));
    }

    /**
     * Function to parse the dictionary file path if provided
     * @param args User arguments passed
     * @return A valid file path
     */
    private static String parseDictionaryFilePath(String[] args) {
        String dictionaryFilePath = null;
        if (args[1].equals(OPT_DICTIONARY)) {
            dictionaryFilePath = args[2];
            ValidatorSvc.INSTANCE.validateFilePath(dictionaryFilePath);
        } else {
            printUsage();
        }
        return dictionaryFilePath;
    }

    /**
     * Function to parse the input file path if provided
     * @param args User arguments passed
     * @return A valid file path
     */
    private static String parseInputFilePath(String[] args) {
        String inputFilePath;
        inputFilePath = args[0];
        ValidatorSvc.INSTANCE.validateFilePath(inputFilePath);
        return inputFilePath;
    }

    /**
     * Function to print usage of this application
     */
    private static void printUsage() {
        log.info("Usage: java Main <phone_number_input_file> -d <dictionary_file_path>");
        log.info("<phone_number_input_file>: Path of the file containing phone number to convert");
        log.info("-d <dictionary_file_path>: Option to provide your own dictionary file");
    }
}
