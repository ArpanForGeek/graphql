package com.example.graphql.annotations.passwordgenerator.processor;

import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.tuple.ValueGenerator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Generator for obtaining the password.
 */
@Component
@NoArgsConstructor
public final class AnnotatedPasswordGenerator {

    private static final Map<Class<?>, ValueGenerator<?>> generator;


    /**
     * static block to set generator map
     */
    static {
        generator = new HashMap<>();
        generator.put(
                String.class,
                (session, owner) -> new PasswordProcessor().password
        );
    }

    /**
     * Retrieve a ValueGenerator for in-VM generation of (non-identifier) attribute values.
     *
     * @param type provided propertyType (key)
     * @param <T>  Type of propertyType
     * @return ValueGenerator
     */
    public static <T> ValueGenerator<T> get(final Class<T> type) {
        final ValueGenerator<?> valueGeneratorSupplier = generator.get(
                type);
        if (Objects.isNull(valueGeneratorSupplier)) {
            throw new HibernateException(
                    "Unsupported property type [" + type.getName() + "] for @PasswordGenerator  generator annotation");
        }
        return (ValueGenerator<T>) valueGeneratorSupplier;
    }
}
