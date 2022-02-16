package com.cami.udemy.graphql.problemz.problemzgraphql.component.problemz;

import com.cami.udemy.graphql.problemz.problemzgraphql.exception.ProblemzAuthenticationException;
import com.cami.udemy.graphql.problemz.problemzgraphql.service.command.ProblemzCommandService;
import com.cami.udemy.graphql.problemz.problemzgraphql.service.query.ProblemzQueryService;
import com.cami.udemy.graphql.problemz.problemzgraphql.service.query.UserzQueryService;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.Problem;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.ProblemCreateInput;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.ProblemResponse;
import com.cami.udemy.graphql.problemz.problemzgraphql.util.GraphqlBeanMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@DgsComponent
public class ProblemDataResolver {

    @Autowired
    private ProblemzQueryService problemzQueryService;

    @Autowired
    private ProblemzCommandService problemzCommandService;

    @Autowired
    private UserzQueryService userzQueryService;


    @DgsData(parentType = "Query", field = "problemLatestList")
    public List<Problem> latestProblems() {
        return problemzQueryService.problemzLatestList().stream()
                .map(GraphqlBeanMapper::mapToGrapghql)
                .collect(Collectors.toList());
    }

    @DgsData(parentType = "Query", field = "problemDetail")
    public Problem getProblemDetail(@InputArgument(name = "id") String problemId) {

        var problemzId = UUID.fromString(problemId);
        var problemz = problemzQueryService.problemzDetail(problemzId)
                .orElseThrow(DgsEntityNotFoundException::new);

        return GraphqlBeanMapper.mapToGrapghql(problemz);
    }

    @DgsData(parentType = "Mutation", field = "problemCreate")
    public ProblemResponse createProblem(
            @RequestHeader(name = "authToken", required = true) String authToken,
            @InputArgument(name = "problem") ProblemCreateInput problemCreateInput) {

        var userz = userzQueryService.findUserzByAuthToken(authToken)
                .orElseThrow(ProblemzAuthenticationException::new);

        var problemz = GraphqlBeanMapper.mapToEntity(problemCreateInput, userz);

        var createdProblemz = problemzCommandService.createProblem(problemz);

        return ProblemResponse.builder()
            .problem(GraphqlBeanMapper.mapToGrapghql(createdProblemz))
            .build();
    }

    @DgsData(parentType = "Subscription", field = "problemAdded")
    public Flux<Problem> subscribeProblemAdded() {
        return null;
    }
}
