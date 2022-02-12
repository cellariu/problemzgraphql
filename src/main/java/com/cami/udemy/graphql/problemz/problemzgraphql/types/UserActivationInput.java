package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserActivationInput {
    private String username;
    private Boolean active;
}
