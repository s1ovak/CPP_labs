package com.s1ovak.lab2.controller;

import com.s1ovak.lab2.entity.Entity;
import com.s1ovak.lab2.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/programm")
public class Controller {

    private Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Entity> getEntity(
            @RequestParam(name = "string", required = false) String string,
            @RequestParam(name = "symbol", required = false) String symbol
    ) {

        Entity entity = service.getEntity(string, symbol);
        if(entity.getErrorMessage()!=null)
            return ResponseEntity.status(400).body(entity);
        return ResponseEntity.ok(entity);
    }
}
