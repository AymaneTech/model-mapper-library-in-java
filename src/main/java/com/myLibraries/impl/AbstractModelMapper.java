package com.myLibraries.impl;

import com.myLibraries.ModelMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

abstract class AbstractModelMapper<S, D> implements ModelMapper<S, D> {

    @Override
    public void map(S source, D destination) {
        throw new UnsupportedOperationException("all args constructors are not supported");
    }

    protected void mapFields(S source, D destination) {
        Arrays.stream(source.getClass().getDeclaredFields())
                .forEach(sourceField -> {
                    sourceField.setAccessible(true);
                    try {
                        final Field destinationField = destination.getClass().getDeclaredField(sourceField.getName());
                        destinationField.setAccessible(true);
                        destinationField.set(destination, sourceField.get(source));
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    protected Object[] getConstructorArgs(S source) {
        return Arrays.stream(source.getClass().getDeclaredFields())
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        return field.get(source);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray();
    }

    protected D getInstance(Constructor<?> constructor, Object[] constructorArgs) {
        try {
            return (D) constructor.newInstance(constructorArgs);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
