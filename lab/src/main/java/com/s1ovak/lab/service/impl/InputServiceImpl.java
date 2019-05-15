package com.s1ovak.lab.service.impl;

import com.s1ovak.lab.models.Input;
import com.s1ovak.lab.models.Output;
import com.s1ovak.lab.repository.InputRepository;
import com.s1ovak.lab.service.InputService;
import com.s1ovak.lab.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service
public class InputServiceImpl implements InputService {

    private final InputRepository inputRepository;
    private final OutputService outputService;

    @Autowired
    public InputServiceImpl(InputRepository inputRepository, OutputService outputService) {
        this.inputRepository = inputRepository;
        this.outputService = outputService;
    }

    @Override
    public Input saveValue(Input value) {
        Input temp = this.inputRepository.findByStringAndSymbol
                (value.getString(), value.getSymbol());

        Output temp1 = this.outputService.saveOutput(value.getOutput());

        if (temp == null) {
            return this.inputRepository.save(value);
        } else {
            return null;
        }

    }
}
