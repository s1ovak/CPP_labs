package com.s1ovak.lab.service.impl;

import com.s1ovak.lab.models.Output;
import com.s1ovak.lab.repository.OutputRepository;
import com.s1ovak.lab.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutputServiceImpl implements OutputService {
    private final OutputRepository outputRepository;

    @Autowired
    public OutputServiceImpl(OutputRepository outputRepository) {
        this.outputRepository = outputRepository;
    }

    @Override
    public Output getByError(Output value) {
        return this.outputRepository.findOutputByErrorMessage(value.getErrorMessage());
    }

    @Override
    public Output getByNumber(Output value) {
        return this.outputRepository.findOutputByNumber(value.getNumber());
    }

    @Override
    public Output saveOutput(Output value) {
        Output temp = this.outputRepository.findOutputByNumberAndErrorMessage
                (value.getNumber(), value.getErrorMessage());

        if (temp == null) {
            return this.outputRepository.save(value);
        } else {
            return null;
        }
    }

    @Override
    public Output getByNumberAndError(Output value) {
        Output temp = this.outputRepository.findOutputByNumberAndErrorMessage(value.getNumber(), value.getErrorMessage());

        if (temp == null) {
            return this.outputRepository.save(value);
        } else {
            return null;
        }
    }
}
