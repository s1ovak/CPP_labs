package com.s1ovak.lab.controller;

import com.s1ovak.lab.entity.Answers;
import com.s1ovak.lab.entity.Entity;
import com.s1ovak.lab.entity.InputList;
import com.s1ovak.lab.entity.StatisticAnswer;
import com.s1ovak.lab.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/programm")
public class Controller {

    private Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getEntity(
            @RequestParam(name = "string", required = false) String string,
            @RequestParam(name = "symbol", required = false) String symbol
    ) {

        Entity entity = service.getEntity(string, symbol);


        if(entity.getErrorMessage()!=null)
            return ResponseEntity.status(400).body(entity);

        return ResponseEntity.ok(entity);
    }


    @PostMapping
    public StatisticAnswer check(@RequestBody InputList list){
        return service.calculateStatistic(list);
    }
}
