package com.s1ovak.lab.service;

import com.s1ovak.lab.cache.CacheMap;
import com.s1ovak.lab.cache.InputParameters;
import com.s1ovak.lab.entity.Entity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Service {

    private static final Logger logger = Logger.getLogger(Service.class);

    @Autowired
    private CacheMap cache;

    /*@Autowired
    public Service(CacheMap cache) {
        this.cache = cache;
    }
*/
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
        Entity cacheResult = this.checkCache(string, symbol);

        if (cacheResult == null) {
            Entity entity = this.calculateSymbol(string, symbol);
            this.addIntoCache(string, symbol, entity);
            return entity;
        }

        return cacheResult;


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

}
