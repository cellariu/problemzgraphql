package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolutionCreateInput {
    private String content;
    private SolutionCategory category;
    private String problemId;
}
