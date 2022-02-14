package com.cami.udemy.graphql.problemz.problemzgraphql.component.problemz;

import com.cami.udemy.graphql.problemz.problemzgraphql.types.Problem;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.ProblemCreateInput;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.ProblemResponse;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

import java.util.List;

@DgsComponent
public class ProblemDataResolver {

    @DgsData(parentType = "Query", field = "problemLatestList")
    public List<Problem> latestProblems() {
        return null;
    }

    @DgsData(parentType = "Query", field = "problemDetail")
    public Problem getProblemDetail(@InputArgument(name = "id") String problemId) {
        return null;
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
