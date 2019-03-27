package com.s1ovak.lab.cache;

import com.s1ovak.lab.entity.Entity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CacheMap {
    private static final Logger logger = Logger.getLogger(CacheMap.class);


    HashMap<InputParameters, Entity> map = new HashMap<>();

    public CacheMap() {
    }


    public void add(InputParameters parameters, Entity entity){
        map.put(parameters, entity);
        logger.info("Component added into cache");
    }

    public HashMap<InputParameters, Entity> getMap() {
        return map;
    }

    public void setMap(HashMap<InputParameters, Entity> map) {
        this.map = map;
    }
}
