package com.myLibraries.impl;

import com.myLibraries.ModelMapper;

import java.lang.reflect.Constructor;

class AllArgsConstructorMapper<S, D> extends AbstractModelMapper<S, D> implements ModelMapper<S, D> {
    private final Constructor<?> constructor;

    public AllArgsConstructorMapper(Constructor<?> constructor) {
        this.constructor = constructor;
    }

    @Override
    public D map(final S source, final Class<D> destinationClass) {
        final Object[] constructorArgs = getConstructorArgs(source);
        return getInstance(constructor, constructorArgs);
    }
}
