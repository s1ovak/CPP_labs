package com.s1ovak.lab2.service;

import com.s1ovak.lab2.controller.Controller;
import com.s1ovak.lab2.entity.Entity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class Service {

    private static final Logger logger = Logger.getLogger(Controller.class);

    public Entity getEntity(String string, String symbol) {

        if (logger.isDebugEnabled()) {
            logger.debug("getEntity method is called!");
        }
        if (string == null) {
            return isError("Invalid params! Need parameter string");
        }
        if (symbol == null) {
            return isError("Invalid params! Need parameter symbol");
        }


        if (symbol.length() != 1) {
            return isError("Invalid params! Param symbol must be char");
        }

        logger.debug("getEntity method is successfully completed");
        return calculateSymbol(string, symbol);
    }


    public Entity calculateSymbol(String string, String symbol) {
        Entity count = new Entity(0);
        char checkedSymbol = symbol.charAt(0);
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == checkedSymbol) {
                count.setNumber(count.getNumber() + 1);
            }
        }

        return count;
    }


    private Entity isError(String msg) {
        logger.error(msg);
        return new Entity(msg);
    }

}
