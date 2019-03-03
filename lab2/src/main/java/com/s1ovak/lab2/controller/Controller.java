package com.s1ovak.lab2.controller;

import com.s1ovak.lab2.entity.NumberOfSymbols;
import com.s1ovak.lab2.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/programm")
public class Controller {

    @Autowired
    Service service;

    @RequestMapping
    public NumberOfSymbols getData(
            @RequestParam(name = "string") String string,
            @RequestParam(name = "symbol") Character symbol
    ) {
        return service.calculateSymbol(string, symbol);
    }
}
