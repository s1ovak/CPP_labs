package com.s1ovak.lab.controller;

import com.s1ovak.lab.cache.Cache;
import com.s1ovak.lab.entity.AnswerModel;
import com.s1ovak.lab.entity.Answers;
import com.s1ovak.lab.entity.InputList;
import com.s1ovak.lab.entity.StatisticAnswer;
import com.s1ovak.lab.service.impl.CalculatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Future;

@RestController
@RequestMapping("api/programm")
public class Controller {

    private CalculatingService service;
    private final Cache cache;

    @Autowired
    public Controller(CalculatingService service, Cache cache) {
        this.service = service;
        this.cache = cache;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getEntity(
            @RequestParam(name = "string", required = false) String string,
            @RequestParam(name = "symbol", required = false) String symbol
    ) {

        AnswerModel answerModel = service.getEntity(string, symbol);


        if (answerModel.getErrorMessage() != null)
            return ResponseEntity.status(400).body(answerModel);

        return ResponseEntity.ok(answerModel);
    }


    @PostMapping
    public StatisticAnswer check(@RequestBody InputList list) {
        return service.calculateStatistic(list);
    }


    //Next 2 methods for asynchronous call(lab 8)
    @PostMapping(value = "/asynchr")
    public Integer checkAndReturnId(@RequestBody InputList list) {
        return service.getResponseId(list);
    }

    @PostMapping("/future")
    public ResponseEntity<?> getAnswers(
            @RequestParam(name = "future_id") Integer futureId
    ) {
        Answers answers = service.getAnswers(futureId);
        if(answers==null)
            return ResponseEntity.ok("This operation is still computing. Try a bit later.");
        return ResponseEntity.ok(answers);
    }
}
