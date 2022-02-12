package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginInput {
    private String username;
    private String password;
}
