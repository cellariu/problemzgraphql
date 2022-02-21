package com.cami.udemy.graphql.problemz.problemzgraphql.service.command;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Problemz;
import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.repository.ProblemzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class ProblemzCommandService {

    private Sinks.Many<Problemz> problemzSink = Sinks.many().multicast().onBackpressureBuffer();

    @Autowired
    private ProblemzRepository problemzRepository;

    public Problemz createProblem(Problemz p) {
        Problemz createdProblemz = problemzRepository.save(p);
        problemzSink.tryEmitNext(createdProblemz);
        return createdProblemz;
    }

    public Flux<Problemz> problemzFlux() {
        return problemzSink.asFlux();
    }
}
