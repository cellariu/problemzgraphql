package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.*;

import java.time.LocalTime;
import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthToken {

    private String id;
    private String authToken;
    private OffsetDateTime createTimestamp;
    private OffsetDateTime expiryTime;
}
