package com.s1ovak.lab.entity;

import com.s1ovak.lab.cache.InputParameters;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
public class InputList {
    private ArrayList<InputParameters> parameters;
}
