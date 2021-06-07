package com.example.graphql.exception.exceptions;

import com.example.graphql.exception.model.ErrorDetails;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class InvalidArgumentException extends IllegalArgumentException implements GraphQLError {
    private static final Integer STATUS_CODE = 400;
    public String invalidField;

    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, String invalidField) {
        super(message);
        this.invalidField = invalidField;
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
    public Map<String, Object> getExtensions() {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), super.getLocalizedMessage(), STATUS_CODE, invalidField);
        return Collections.singletonMap("error details", errorDetails);
    }
}