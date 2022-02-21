package com.cami.udemy.graphql.problemz.problemzgraphql.service.command;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Problemz;
import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Solutionz;
import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.repository.SolutionzRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Optional;
import java.util.UUID;

@Service
public class SolutionzCommandService {

    private Sinks.Many<Solutionz> solutionzSink = Sinks.many().multicast().onBackpressureBuffer();

    @Autowired
    private SolutionzRespository solutionzRespository;

    public Solutionz createSolutionz(Solutionz s) {
        Solutionz createdSolutionz = solutionzRespository.save(s);

        solutionzSink.tryEmitNext(createdSolutionz);

        return createdSolutionz;
    }

    public Optional<Solutionz> voteAsBad(UUID solutionId) {
        solutionzRespository.addVoteBadCount(solutionId);

        var updated = solutionzRespository.findById(solutionId);
        if (updated.isPresent()) {
            solutionzSink.tryEmitNext(updated.get());
        }

        return solutionzRespository.findById(solutionId);
    }

    public Optional<Solutionz> voteAsGood(UUID solutionId) {
        solutionzRespository.addVoteGoodCount(solutionId);

        var updated = solutionzRespository.findById(solutionId);
        if (updated.isPresent()) {
            solutionzSink.tryEmitNext(updated.get());
        }

        return solutionzRespository.findById(solutionId);
    }

    public Flux<Solutionz> solutionzFlux() {
        return solutionzSink.asFlux();
    }
}
