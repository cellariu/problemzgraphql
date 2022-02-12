package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.*;

import java.time.LocalTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthToken {
    private String authToken;
    private LocalTime expiryTime;
}
