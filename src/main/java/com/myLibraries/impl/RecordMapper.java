package com.myLibraries.impl;

import com.myLibraries.ModelMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

class RecordMapper<S, D> extends AbstractModelMapper<S, D> implements ModelMapper<S, D> {
    @Override
    public D map(final S source, final Class<D> destinationClass) {
        try {
            final Constructor<?> constructor = destinationClass.getDeclaredConstructor(
                    Arrays.stream(destinationClass.getDeclaredFields())
                            .map(Field::getType)
                            .toArray(Class[]::new)
            );

            final Object[] constructorArgs = getConstructorArgs(source);

            return getInstance(constructor, constructorArgs);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
