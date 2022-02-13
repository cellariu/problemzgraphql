package com.cami.udemy.graphql.problemz.problemzgraphql.types;

import lombok.*;

import java.net.URL;
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
    private URL avatar;
    private LocalTime createDateTime;
    private String displayName;
    @NonNull private List<Problem> problems;

}
