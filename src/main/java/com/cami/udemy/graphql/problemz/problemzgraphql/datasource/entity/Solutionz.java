package com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "solutionz")
@Getter
@Setter
public class Solutionz {

    @Id
    private UUID id;

    private String category;
    private String content;

    @CreationTimestamp
    private LocalDateTime creationTimeStamp;

    private int voteBadCount;
    private int voteGoodCount;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Userz createdBy;

    @ManyToOne
    @JoinColumn(name = "problemz_id", nullable = false)
    private Problemz problemz;
}
