package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.*;

import java.net.URI;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateInput {

    private String username;
    private String email;
    private String password;
    private String displayName;
    private URI avatar;
}
