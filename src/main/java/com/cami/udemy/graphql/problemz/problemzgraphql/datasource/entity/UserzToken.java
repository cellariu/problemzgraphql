package com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "userz_token")
@Getter
@Setter
public class UserzToken {

    @Id
    private UUID userId;

    private String authToken;

    @CreationTimestamp
    private LocalDateTime createTimestamp;

    @CreationTimestamp
    private LocalDateTime expiryTimestamp;
}
