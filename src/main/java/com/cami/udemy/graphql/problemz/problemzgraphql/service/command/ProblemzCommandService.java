package com.cami.udemy.graphql.problemz.problemzgraphql.service.command;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Problemz;
import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.repository.ProblemzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemzCommandService {

    @Autowired
    private ProblemzRepository problemzRepository;

    public Problemz createProblem(Problemz p) {
        return problemzRepository.save(p);
    }

}
