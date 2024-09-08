package com.myLibraries.impl;

import com.myLibraries.ModelMapper;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class ModelMapperFactory {
    public static <S, D> ModelMapper<S, D> get(Class<D> destinationClass) {
        if (destinationClass.isRecord()) {
            return new RecordMapper<>();
        } else {
            Constructor<?> allArgsConstructor = Arrays.stream(destinationClass.getDeclaredConstructors())
                    .filter(c -> c.getParameterCount() == destinationClass.getDeclaredFields().length)
                    .findFirst()
                    .orElse(null);

            if (allArgsConstructor != null) {
                return new AllArgsConstructorMapper<>(allArgsConstructor);
            } else {
                return new SimpleModelMapper<>();
            }
        }
    }
}
