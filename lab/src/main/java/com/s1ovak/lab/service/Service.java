package com.s1ovak.lab.service;

import com.s1ovak.lab.cache.CacheMap;
import com.s1ovak.lab.cache.InputParameters;
import com.s1ovak.lab.counter.Counter;
import com.s1ovak.lab.entity.Answers;
import com.s1ovak.lab.entity.Entity;
import com.s1ovak.lab.entity.InputList;
import com.sun.deploy.util.ArrayUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;


@Component
public class Service {

    private static final Logger logger = Logger.getLogger(Service.class);

    private Counter counter;

    private CacheMap cache;

    @Autowired
    public Service(CacheMap cache, Counter counter) {
        this.cache = cache;
        this.counter = counter;
    }

    public Entity checkCache(String string, String symbol) {
        return cache.getMap().get(new InputParameters(string, symbol));
    }

    public void addIntoCache(String string, String symbol, Entity entity) {
        cache.add(new InputParameters(string, symbol), entity);
    }

    public Entity getEntity(String string, String symbol) {

        if (logger.isDebugEnabled()) {
            logger.debug("getEntity method is called!");
        }
        if (string == null || string.equals("")) {
            return isError("Invalid params! Need parameter string");
        }

        if (symbol == null || symbol.equals("")) {
            return isError("Invalid params! Need parameter symbol");
        }


        if (symbol.length() != 1) {
            return isError("Invalid params! Param symbol must be char");
        }

        logger.debug("getEntity method is successfully completed");
        this.increment();

        Entity cacheResult = this.checkCache(string, symbol);


        if (cacheResult == null) {
            Entity entity = this.calculateSymbol(string, symbol);
            this.addIntoCache(string, symbol, entity);
            return entity;
        }

        return cacheResult;


    }

    public synchronized void increment() {
        counter.inc();
        logger.info("Incremented count, current: " + counter.getCounter());
    }

    public Entity calculateSymbol(String string, String symbol) {
        Entity count = new Entity(0);
        char checkedSymbol = symbol.charAt(0);
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == checkedSymbol) {
                count.setNumber(count.getNumber() + 1);
            }
        }

        return count;
    }

    private Entity isError(String msg) {
        logger.error(msg);
        return new Entity(msg);
    }


    public Answers checkList(InputList list) {
        Answers answers = new Answers();

        list.getParameters().forEach(value -> {
            char[] chars = value.getString().toCharArray();
            answers.add(evaluate(boxChar(chars),
                    (n) -> n == value.getSymbol().charAt(0)));
        });
        return answers;
    }


    public static Character[] boxChar(char[] chars) {
        return IntStream.range(0, chars.length)
                .mapToObj((i) -> chars[i])
                .toArray(Character[]::new);
    }

    public static Integer evaluate(Character[] characters, Predicate<Character> predicate) {
        Integer i = 0;

        for (Character n : characters) {
            if (predicate.test(n)) {
                i++;
            }
        }

        return i;
    }
}