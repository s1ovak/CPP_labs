package com.s1ovak.lab.service;

import com.s1ovak.lab.models.Output;

public interface OutputService {
    Output getByError(Output value);
    Output getByNumber(Output value);
    Output saveOutput(Output value);
    Output getByNumberAndError(Output value);
}
