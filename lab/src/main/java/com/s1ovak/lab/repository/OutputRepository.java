package com.s1ovak.lab.repository;

import com.s1ovak.lab.models.Output;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRepository extends CrudRepository<Output, String> {
    Output findOutputByErrorMessage(String errorMessage);
    Output findOutputByNumber(Integer number);
    Output findOutputByNumberAndErrorMessage(Integer number, String errorMessage);
}
