package com.s1ovak.lab.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
public class Answers {
    private ArrayList<Entity> resultList;

    public Answers(){
        resultList = new ArrayList<>();
    }

    public void add(Entity value){
        this.resultList.add(value);
    }
}
