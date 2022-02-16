package com.cami.udemy.graphql.problemz.problemzgraphql.exception.handler;

import com.cami.udemy.graphql.problemz.problemzgraphql.exception.ProblemzAuthenticationException;
import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler;
import com.netflix.graphql.types.errors.ErrorType;
import com.netflix.graphql.types.errors.TypedGraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ProblemzGrapqlExceptionHandler implements DataFetcherExceptionHandler {

    private final DefaultDataFetcherExceptionHandler defaultHandler = new DefaultDataFetcherExceptionHandler();

    @Override
    public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handlerParameters) {

        var exception = handlerParameters.getException();

        if (exception instanceof ProblemzAuthenticationException) {
            var grapghqlError = TypedGraphQLError.newBuilder()
                    .message(exception.getMessage())
                    .path(handlerParameters.getPath())
                    //.errorType(ErrorType.UNAUTHENTICATED)
                    .errorDetail(new ProblemzErrorDetail())
                    .build();

            return DataFetcherExceptionHandlerResult.newResult().error(grapghqlError).build();
        }

        return defaultHandler.onException(handlerParameters);
    }
}
