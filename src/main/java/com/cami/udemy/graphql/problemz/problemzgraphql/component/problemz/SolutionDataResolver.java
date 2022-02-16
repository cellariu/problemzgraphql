package com.cami.udemy.graphql.problemz.problemzgraphql.component.problemz;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Solutionz;
import com.cami.udemy.graphql.problemz.problemzgraphql.exception.ProblemzAuthenticationException;
import com.cami.udemy.graphql.problemz.problemzgraphql.service.command.SolutionzCommandService;
import com.cami.udemy.graphql.problemz.problemzgraphql.service.query.ProblemzQueryService;
import com.cami.udemy.graphql.problemz.problemzgraphql.service.query.UserzQueryService;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.Solution;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.SolutionCreateInput;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.SolutionResponse;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.SolutionVoteInput;
import com.cami.udemy.graphql.problemz.problemzgraphql.util.GraphqlBeanMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.UUID;

@DgsComponent
public class SolutionDataResolver {

    @Autowired
    private SolutionzCommandService solutionzCommandService;

    @Autowired
    private ProblemzQueryService problemzQueryService;

    @Autowired
    private UserzQueryService userzQueryService;

    @DgsData(parentType = "Mutation", field = "solutionCreate")
    public SolutionResponse createSolution(
            @RequestHeader(name = "authToken", required = true) String authToken,
            @InputArgument(name = "solution") SolutionCreateInput solutionCreateInput) {

        var userz = userzQueryService.findUserzByAuthToken(authToken)
                .orElseThrow(ProblemzAuthenticationException::new);

        var problemzId = UUID.fromString(solutionCreateInput.getProblemId());

        var problemz = problemzQueryService.problemzDetail(problemzId)
                .orElseThrow(DgsEntityNotFoundException::new);

        var solutionz = GraphqlBeanMapper.mapToEntity(solutionCreateInput, userz, problemz);

        var solutionzCreated = solutionzCommandService.createSolutionz(solutionz);

        return SolutionResponse.builder()
                .solution(GraphqlBeanMapper.mapToGrapghql(solutionzCreated))
                .build();
    }

    @DgsData(parentType = "Mutation", field = "solutionVote")
    public SolutionResponse voteSolution(
            @RequestHeader(name = "authToken", required = true) String authToken,
            @InputArgument(name = "vote") SolutionVoteInput solutionVoteInput) {

        var userz = userzQueryService.findUserzByAuthToken(authToken)
                .orElseThrow(ProblemzAuthenticationException::new);

        Optional<Solutionz> updatedSolutionz;

        if (solutionVoteInput.getVoteAsGood()) {
            updatedSolutionz = solutionzCommandService.voteAsGood(UUID.fromString(solutionVoteInput.getSolutionId()));
        } else {
            updatedSolutionz = solutionzCommandService.voteAsBad(UUID.fromString(solutionVoteInput.getSolutionId()));
        }

        if (updatedSolutionz.isEmpty()) {
            throw new DgsEntityNotFoundException("Solutionz with " + solutionVoteInput.getSolutionId() + " not found");
        }

        return SolutionResponse.builder()
                .solution(GraphqlBeanMapper.mapToGrapghql(updatedSolutionz.get()))
                .build();
    }

    @DgsData(parentType = "Subscription", field = "solutionVoteChanged")
    public Flux<Solution> changeVoteForSolution(@InputArgument(name = "solutionId") String solutionId) {
        return null;
    }
}
