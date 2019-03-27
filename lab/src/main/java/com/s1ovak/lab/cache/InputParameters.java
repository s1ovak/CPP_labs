package com.s1ovak.lab.cache;


import java.util.Objects;

public class InputParameters {
    private String string;
    private String symbol;

    public InputParameters(String string, String symbol) {
        this.string = string;
        this.symbol = symbol;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputParameters that = (InputParameters) o;
        return Objects.equals(string, that.string) &&
                Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string, symbol);
    }
}
