package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.*;
import org.jetbrains.annotations.NotNull;


import java.net.URI;
import java.time.LocalTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
    private String username;
    private String email;
    private URI avatar;
    private LocalTime createDateTime;
    private String displayName;
    @NotNull private List<Problem> problems;

}
