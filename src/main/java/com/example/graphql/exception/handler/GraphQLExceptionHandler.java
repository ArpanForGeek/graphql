package com.example.graphql.exception.handler;

import com.example.graphql.exception.exceptions.InvalidAuthenticationException;
import graphql.ExceptionWhileDataFetching;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import graphql.execution.ExecutionPath;
import graphql.language.SourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;

public class GraphQLExceptionHandler implements DataFetcherExceptionHandler {

    protected static final Logger logger = LogManager.getLogger("demo_graphql");

    @Override
    public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handlerParameters) {
        Throwable exception = handlerParameters.getException();
        SourceLocation sourceLocation = handlerParameters.getSourceLocation();
        ExecutionPath path = handlerParameters.getPath();
        if (exception instanceof AccessDeniedException) {
            logger.warn("unauthorized to access ");
            return DataFetcherExceptionHandlerResult.newResult().error(new InvalidAuthenticationException("unauthorized to access ", "user")).build();
        } else if (exception instanceof InvalidAuthenticationException) {
            logger.warn("user is inactive ");
            return DataFetcherExceptionHandlerResult.newResult().error(new InvalidAuthenticationException("user is inactive ", "inactive user")).build();
        }

        ExceptionWhileDataFetching error = new ExceptionWhileDataFetching(path, exception, sourceLocation);
        return DataFetcherExceptionHandlerResult.newResult().error(error).build();
    }
}
