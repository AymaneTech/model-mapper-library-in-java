package com.myLibraries;

public interface ModelMapper<S, D> {
    D map(S source, Class<D> destinationClass);

    void map(S source, D destination);
}
