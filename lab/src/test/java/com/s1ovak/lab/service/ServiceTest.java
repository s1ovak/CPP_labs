package com.s1ovak.lab.service;

import com.s1ovak.lab.entity.AnswerModel;
import com.s1ovak.lab.service.impl.CalculatingService;
import org.junit.Assert;
import org.junit.Test;

public class ServiceTest {

    CalculatingService service;



    @Test
    public void calculateSymbol() {

        AnswerModel expected = service.calculateSymbol("asdjhfjgasfhraa","a");
        AnswerModel actual = new AnswerModel(4);

        Assert.assertEquals(expected.getNumber(), actual.getNumber());

        expected = service.calculateSymbol("asdjhfjgasfhraa","a");
        actual.setNumber(5);

        Assert.assertNotEquals(expected, actual);
    }
}
