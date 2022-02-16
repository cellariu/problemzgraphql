package com.cami.udemy.graphql.problemz.problemzgraphql.component.problemz;

import com.cami.udemy.graphql.problemz.problemzgraphql.service.query.ProblemzQueryService;
import com.cami.udemy.graphql.problemz.problemzgraphql.service.query.SolutionzQueryService;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.SearchableItem;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.SearchableItemFilter;
import com.cami.udemy.graphql.problemz.problemzgraphql.util.GraphqlBeanMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class ItemSearchDataResolver {

    @Autowired
    private ProblemzQueryService problemzQueryService;

    @Autowired
    private SolutionzQueryService solutionzQueryService;

    @DgsData(parentType = "Query", field = "itemSearch")
    public List<SearchableItem> searchItems(@InputArgument(name = "filter") SearchableItemFilter searchableItemFilter) {
        var result = new ArrayList<SearchableItem>();
        var keyword = searchableItemFilter.getKeyword();

        var problemzByKeyword = problemzQueryService.problemzByKeyword(keyword).stream()
                .map(GraphqlBeanMapper::mapToGrapghql).collect(Collectors.toList());

        result.addAll(problemzByKeyword);

        var solutionzByKeyword = solutionzQueryService.solutionzByKeyword(keyword).stream()
                .map(GraphqlBeanMapper:: mapToGrapghql)
                .collect(Collectors.toList());

        result.addAll(solutionzByKeyword);

        if (result.isEmpty()) {
            throw new DgsEntityNotFoundException("No item with keyword: " + keyword + " was found!");
        }

        result.sort(Comparator.comparing(SearchableItem::getCreateDateTime).reversed());

        return result;
    }
}
