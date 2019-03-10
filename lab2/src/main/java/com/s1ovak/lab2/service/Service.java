package com.s1ovak.lab2.service;

import com.s1ovak.lab2.entity.NumberOfSymbols;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class Service {

    private static final Logger logger = Logger.getLogger(Service.class.getName());

    public NumberOfSymbols calculateSymbol(String string, char symbol) {
        NumberOfSymbols count = new NumberOfSymbols(0);
        logger.info("This is log info");
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == symbol) {
                count.setNumber(count.getNumber()+1);
            }
        }

        return count;
    }

}
