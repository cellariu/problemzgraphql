package com.cami.udemy.graphql.problemz.problemzgraphql.service.command;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Solutionz;
import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.repository.SolutionzRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SolutionzCommandService {

    @Autowired
    private SolutionzRespository solutionzRespository;

    public Solutionz createSolutionz(Solutionz s) {
        return solutionzRespository.save(s);
    }

    public Optional<Solutionz> voteAsBad(UUID solutionId) {
        solutionzRespository.addVoteBadCount(solutionId);
        return solutionzRespository.findById(solutionId);
    }

    public Optional<Solutionz> voteAsGood(UUID solutionId) {
        solutionzRespository.addVoteGoodCount(solutionId);
        return solutionzRespository.findById(solutionId);
    }
}
