package com.example.graphql.annotations.passwordgenerator.annotation;

import com.example.graphql.annotations.passwordgenerator.processor.PasswordGeneration;
import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * Generate random password.It can be used for any field or method level.
 */
@ValueGenerationType(generatedBy = PasswordGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD})
public @interface GeneratePassword {
}
