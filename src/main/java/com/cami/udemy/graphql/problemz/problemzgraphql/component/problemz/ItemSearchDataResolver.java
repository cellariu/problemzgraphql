package com.cami.udemy.graphql.problemz.problemzgraphql.component.problemz;

import com.cami.udemy.graphql.problemz.problemzgraphql.types.SearchableItem;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.SearchableItemFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class ItemSearchDataResolver {

    @DgsData(parentType = "Query", field = "itemSearch")
    public List<SearchableItem> searchItems(@InputArgument(name = "filter") SearchableItemFilter searchableItemFilter) {
        return null;
    }
}
