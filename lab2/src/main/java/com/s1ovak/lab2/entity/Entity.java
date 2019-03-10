package com.s1ovak.lab2.entity;

public class Entity {
    private String errorMessage;
    private Integer number;

    public Entity() {
    }

    public Entity(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public Entity(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
