package com.s1ovak.lab.entity;

import lombok.Data;

@Data
public class StatisticAnswer {
    Answers answers;
    Integer totalInputAmount;
    Long totalIncorrectInputAmount;
    Integer maxResult;
    Integer minResult;
    Integer mostPopular;
}
