package com.s1ovak.lab.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "output")
@Data
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "error_message")
    private String errorMessage;
    @Column(name = "number")
    private Integer number;

    public Output(String errorMessage, Integer number) {
        this.errorMessage = errorMessage;
        this.number = number;
    }

    public Output() {
    }
}
