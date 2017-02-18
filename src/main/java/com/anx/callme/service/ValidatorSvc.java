package com.anx.callme.service;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Service to validate stuff, as this deals with the business logic we want this to be singelton.
 *
 * Created by agrawald on 17/02/17.
 */
@Slf4j
public enum ValidatorSvc {
    INSTANCE;

    public void validateFilePath(String inputFilePath) {
        if(!Files.exists(Paths.get(inputFilePath))) {
            log.error("Invalid input file provided");
            System.exit(1);
        }
    }
}
