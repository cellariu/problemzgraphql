package com.cami.udemy.graphql.problemz.problemzgraphql.component.problemz;

import com.cami.udemy.graphql.problemz.problemzgraphql.types.Solution;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.SolutionCreateInput;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.SolutionResponse;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.SolutionVoteInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

@DgsComponent
public class SolutionDataResolver {

    @DgsData(parentType = "Mutation", field = "solutionCreate")
    public SolutionResponse createSolution(
            @RequestHeader(name = "authToken", required = true) String authToken,
            @InputArgument(name = "solution") SolutionCreateInput solutionCreateInput) {
        return null;
    }

    @DgsData(parentType = "Mutation", field = "solutionVote")
    public SolutionResponse voteSolution(
            @RequestHeader(name = "authToken", required = true) String authToken,
            @InputArgument(name = "vote") SolutionVoteInput solutionVoteInput) {
        return null;
    }

    @DgsData(parentType = "Subscription", field = "solutionVoteChanged")
    public Flux<Solution> changeVoteForSolution(@InputArgument(name = "solutionId") String solutionId) {
        return null;
    }
}
