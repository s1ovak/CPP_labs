package com.s1ovak.lab.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "input")
@Data
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "string")
    private String string;
    @Column(name = "symbol")
    private String symbol;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "output_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Output output;

    public Input(String string, String symbol, Output output) {
        this.string = string;
        this.symbol = symbol;
        this.output = output;
    }
}
