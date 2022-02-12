package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Problem implements SearchableItem {

    private String id;
    private LocalDate createDateTime;
    private String prettyCreateDateTime;
    private String content;
    private String title;
    private List<String> tags;
    private Integer solutionCount;
    @NonNull List<Solution> solutions;
}
