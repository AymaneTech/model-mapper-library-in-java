package com.myLibraries.impl;

import com.myLibraries.ModelMapper;

import java.lang.reflect.InvocationTargetException;

class SimpleModelMapper<S, D> extends AbstractModelMapper<S, D> implements ModelMapper<S, D> {
    @Override
    public D map(final S source, final Class<D> destinationClass) {
        try {
            final D destination = destinationClass.getDeclaredConstructor().newInstance();

            mapFields(source, destination);
            return destination;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void map(final S source, final D destination) {
        try {
            mapFields(source, destination);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
