package com.cami.udemy.graphql.problemz.problemzgraphql.exception;

public class ProblemzAuthenticationException extends RuntimeException {

    public ProblemzAuthenticationException() {
        super("Invalid Credentials");
    }
}
