package com.cami.udemy.graphql.problemz.problemzgraphql.component.problemz;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Problemz;
import com.cami.udemy.graphql.problemz.problemzgraphql.service.query.ProblemzQueryService;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.Problem;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.ProblemCreateInput;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.ProblemResponse;
import com.cami.udemy.graphql.problemz.problemzgraphql.util.GraphqlBeanMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@DgsComponent
public class ProblemDataResolver {

    @Autowired
    private ProblemzQueryService problemzQueryService;

    @DgsData(parentType = "Query", field = "problemLatestList")
    public List<Problem> latestProblems() {
        return problemzQueryService.problemzLatestList().stream()
                .map(GraphqlBeanMapper::mapToGrapghql)
                .collect(Collectors.toList());
    }

    @DgsData(parentType = "Query", field = "problemDetail")
    public Problem getProblemDetail(@InputArgument(name = "id") String problemId) {
        Optional<Problemz> optionalProblemz = problemzQueryService.problemzDetail(UUID.fromString(problemId));

        if(optionalProblemz.isEmpty()) {
            Optional<Problem> noProblem = Optional.empty();
            return noProblem.get();
        }
        return GraphqlBeanMapper.mapToGrapghql(optionalProblemz.get());
    }

    @DgsData(parentType = "Mutation", field = "problemCreate")
    public ProblemResponse createProblem(
            @RequestHeader(name = "authToken", required = true) String authToken,
            @InputArgument(name = "problem") ProblemCreateInput problemCreateInput) {
        return null;
    }

    @DgsData(parentType = "Subscription", field = "problemAdded")
    public Flux<Problem> subscribeProblemAdded() {
        return null;
    }
}
