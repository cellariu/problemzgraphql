package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemCreateInput {
    private String title;
    private String content;
    @NonNull private List<String> tags;

}
