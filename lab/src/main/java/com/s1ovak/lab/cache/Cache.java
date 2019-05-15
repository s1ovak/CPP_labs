package com.s1ovak.lab.cache;

import com.s1ovak.lab.entity.AnswerModel;
import com.s1ovak.lab.entity.Answers;
import com.s1ovak.lab.entity.InputList;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.Future;

@Component
@Data
public class Cache {

    private HashMap<Integer, Future<Answers>> map = new HashMap<>();

    Integer currentId = 0;

    public Cache() {

    }


    public Integer add(Future<Answers> list){
        map.put(++currentId, list);
        return currentId;
    }
}
