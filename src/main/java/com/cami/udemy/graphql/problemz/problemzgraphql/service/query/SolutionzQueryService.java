package com.cami.udemy.graphql.problemz.problemzgraphql.service.query;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Solutionz;
import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.repository.SolutionzRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionzQueryService {

    @Autowired
    private SolutionzRespository repository;

    public List<Solutionz> solutionzByKeyword(String keyword) {
        return repository.findByKeyword("%" + keyword + "%");
    }

}
