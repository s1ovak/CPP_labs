package com.s1ovak.lab.cache;

import com.s1ovak.lab.entity.AnswerModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CacheMap {
    private static final Logger logger = Logger.getLogger(CacheMap.class);

    HashMap<InputParameters, AnswerModel> map = new HashMap<>();

    public CacheMap() {
    }


    public void add(InputParameters parameters, AnswerModel answerModel){
        map.put(parameters, answerModel);
        logger.info("Component added into cache");
    }

    public HashMap<InputParameters, AnswerModel> getMap() {
        return map;
    }

    public void setMap(HashMap<InputParameters, AnswerModel> map) {
        this.map = map;
    }
}
