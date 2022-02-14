package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public interface SearchableItem {

    String getId();
    OffsetDateTime getCreateDateTime();
    String getPrettyCreateDateTime();
    String getContent();
}
