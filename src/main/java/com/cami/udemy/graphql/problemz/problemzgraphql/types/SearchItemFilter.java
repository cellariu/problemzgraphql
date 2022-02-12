package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchItemFilter {
    private String keyword;
}
