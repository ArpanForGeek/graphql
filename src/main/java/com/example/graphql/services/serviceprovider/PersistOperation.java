package com.example.graphql.services.serviceprovider;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This interface contains methods to perform persistent  operation
 *
 * @param <T> Any Java Entity Class
 */
public interface PersistOperation<T> {

    /**
     * Performs save operation
     *
     * @param t Entity
     * @return true for successful operation
     */
    @Transactional
    default boolean save(T t) {
        return false;
    }

    /**
     * Performs delete operation based on provided data
     *
     * @param s data provided by user
     * @return true for successful operation
     */
    @Transactional
    default boolean delete(String s) {
        return false;
    }

    /**
     * Performs delete operation based on provided data
     *
     * @param s data provided by user
     * @return true for successful operation
     */
    @Transactional
    default boolean delete(Integer s) {
        return false;
    }

    /**
     * Performs delete operation based on provided data
     *
     * @param s data provided by user
     * @return true for successful operation
     */
    @Transactional
    default boolean delete(Long s) {
        return false;
    }

    /**
     * Performs delete operation
     *
     * @return true for successful operation
     */
    @Transactional
    default boolean deleteAll() {
        return false;
    }

    /**
     * Performs save  operation for List of entities
     *
     * @param t List of Entity
     * @return true for successful operation
     */
    @Transactional
    default boolean saveAll(List<T> t) {
        return false;
    }

}
