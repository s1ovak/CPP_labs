package com.s1ovak.lab.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Answers {
    private ArrayList<AnswerModel> resultList;

    public Answers(){
        resultList = new ArrayList<>();
    }

    public void add(AnswerModel value){
        this.resultList.add(value);
    }
}
