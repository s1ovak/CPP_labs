package com.s1ovak.lab.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
public class AnswerModel {
    private String errorMessage;
    private Integer number;

    public AnswerModel() {
    }

    public AnswerModel(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public AnswerModel(Integer number) {
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
