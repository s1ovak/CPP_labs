package com.s1ovak.lab.counter;


import org.springframework.stereotype.Component;

@Component
public class Counter {
    private Integer counter;

    public Counter() {
        counter = 0;
    }

    public Integer getCounter() {
        return counter;
    }

    public void  inc(){
        counter++;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
