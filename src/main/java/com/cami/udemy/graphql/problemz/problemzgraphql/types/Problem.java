package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Problem implements SearchableItem {

    private String id;
    private String title;
    private String content;
    private OffsetDateTime createDateTime;
    private String prettyCreateDateTime;
    private List<String> tags;
    private Integer solutionCount;
    @NonNull List<Solution> solutions;
    private User author;
}
