package com.s1ovak.lab.service;

import com.s1ovak.lab.cache.CacheMap;
import com.s1ovak.lab.entity.Entity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServiceTest {

    Service service;



    @Test
    public void calculateSymbol() {

        Entity expected = service.calculateSymbol("asdjhfjgasfhraa","a");
        Entity actual = new Entity(4);

        Assert.assertEquals(expected.getNumber(), actual.getNumber());

        expected = service.calculateSymbol("asdjhfjgasfhraa","a");
        actual.setNumber(5);

        Assert.assertNotEquals(expected, actual);
    }
}