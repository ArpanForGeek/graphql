package com.example.graphql.annotations.passwordgenerator.processor;

import com.example.graphql.annotations.passwordgenerator.annotation.GeneratePassword;
import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;
import org.springframework.stereotype.Component;

/**
 * * Value generation implementation for {@link GeneratePassword}.
 */
@Component
public class PasswordGeneration implements AnnotationValueGeneration<GeneratePassword> {

    private ValueGenerator<?> generator;


    @Override
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.INSERT;
    }

    @Override
    public ValueGenerator<?> getValueGenerator() {
        return (ValueGenerator) generator;
    }

    @Override
    public boolean referenceColumnInSql() {
        return false;
    }

    @Override
    public String getDatabaseGeneratedReferencedColumnValue() {
        return null;
    }

    @Override
    public void initialize(GeneratePassword annotation, Class<?> propertyType) {
        generator = AnnotatedPasswordGenerator.get(propertyType);
    }
}
