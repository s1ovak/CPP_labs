package com.s1ovak.lab1.service;

import com.s1ovak.lab1.entity.NumberOfSymbols;

public class Service {
    public static NumberOfSymbols calculateSymbol(String string, char symbol) {
        NumberOfSymbols count = new NumberOfSymbols(0);
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == symbol) {
                count.setNumber(count.getNumber()+1);
            }
        }

        return count;
    }

}
