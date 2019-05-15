package com.s1ovak.lab.repository;

import com.s1ovak.lab.models.Input;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRepository extends CrudRepository<Input, String> {

    Input findByStringAndSymbol(String string, String symbol);
}
