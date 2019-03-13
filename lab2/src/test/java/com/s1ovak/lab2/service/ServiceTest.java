package com.s1ovak.lab2.service;

import com.s1ovak.lab2.entity.Entity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

    Service service;

    @Before
    public void init() {
        service = new Service();
    }

    @Test
    public void calculateSymbol() {

        Entity expected = service.calculateSymbol("asdjhfjgasfhraa","a");
        Entity actual = new Entity(4);

        Assert.assertEquals(expected.getNumber(), actual.getNumber());
    }
}