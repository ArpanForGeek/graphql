package com.example.graphql.exception.exceptions;

import com.example.graphql.exception.model.ErrorDetails;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class AuthenticationServiceException extends InternalAuthenticationServiceException implements GraphQLError {

    private static final Integer STATUS_CODE = 401;

    public AuthenticationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationServiceException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }

    @Override
    public Map<String, Object> toSpecification() {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), super.getLocalizedMessage(), STATUS_CODE);
        return Collections.singletonMap("error details", errorDetails);
    }
}
