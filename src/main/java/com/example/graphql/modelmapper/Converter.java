package com.example.graphql.modelmapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Converter<S,D> {
    D convert(S source);

//    default List<D> convertAll(List<S> sources){
//        return sources.stream().map(this::convert).collect(Collectors.toList());
//    }

    default Set<D> convertAll(Set<S> sources){
        return sources.stream().map(this::convert).collect(Collectors.toSet());
    }
}
