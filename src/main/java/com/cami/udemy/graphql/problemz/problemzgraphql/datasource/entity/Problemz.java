package com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "problemz")
@Getter
@Setter
public class Problemz {

    @Id
    private UUID id;

    private String title;
    private String content;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    private String tags;

    @OneToMany(mappedBy = "problemz")
    @OrderBy("creationTimestamp desc")
    private List<Solutionz> solutions;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Userz createdBy;
}
