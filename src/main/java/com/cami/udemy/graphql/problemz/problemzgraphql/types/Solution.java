package com.cami.udemy.graphql.problemz.problemzgraphql.types;


import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Solution implements SearchableItem {

    private String id;
    private LocalDate createDateTime;
    private String prettyCreateDateTime;
    private String content;
    private SolutionCategory category;
    private Integer voteAsGoodCount;
    private Integer voteAsBadCount;

}
