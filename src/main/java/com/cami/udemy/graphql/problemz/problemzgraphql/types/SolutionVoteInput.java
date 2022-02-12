package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolutionVoteInput {
    private String solutionId;
    private Boolean voteAsGood;
}
