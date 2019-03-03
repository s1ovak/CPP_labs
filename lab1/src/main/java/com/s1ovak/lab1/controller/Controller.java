package com.s1ovak.lab1.controller;

import com.s1ovak.lab1.entity.NumberOfSymbols;
import com.s1ovak.lab1.service.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/programm")
public class Controller {

    @RequestMapping
    public NumberOfSymbols getData(
            @RequestParam(name = "string") String string,
            @RequestParam(name = "symbol") Character symbol
    ) {
        return Service.calculateSymbol(string, symbol);
    }
}
