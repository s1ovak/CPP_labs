package com.s1ovak.lab2.service;

import com.s1ovak.lab2.entity.NumberOfSymbols;
import org.springframework.stereotype.Component;

@Component
public class Service {
    public NumberOfSymbols calculateSymbol(String string, char symbol) {
        NumberOfSymbols count = new NumberOfSymbols(0);
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == symbol) {
                count.setNumber(count.getNumber()+1);
            }
        }

        return count;
    }

}
