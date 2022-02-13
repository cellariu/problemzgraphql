package com.cami.udemy.graphql.problemz.problemzgraphql.types;


import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Solution implements SearchableItem {

    private String id;
    private String content;
    private SolutionCategory category;
    private OffsetDateTime createDateTime;
    private String prettyCreateDateTime;
    private Integer voteAsGoodCount;
    private Integer voteAsBadCount;
    private User createdBy;

}
