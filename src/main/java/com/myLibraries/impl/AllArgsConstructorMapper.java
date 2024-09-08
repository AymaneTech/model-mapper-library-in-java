package com.myLibraries.impl;

import com.myLibraries.ModelMapper;

import java.lang.reflect.Constructor;
import java.util.Arrays;

class AllArgsConstructorMapper<S, D> extends AbstractModelMapper<S, D> implements ModelMapper<S, D> {

    @Override
    public D map(final S source, final Class<D> destinationClass) {
        final  Constructor<?> constructor = Arrays.stream(destinationClass.getDeclaredConstructors())
                    .filter(c -> c.getParameterCount() == destinationClass.getDeclaredFields().length)
                    .findFirst()
                    .orElse(null);

        final Object[] constructorArgs = getConstructorArgs(source);
        return getInstance(constructor, constructorArgs);
    }
}
