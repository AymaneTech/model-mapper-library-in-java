package com.myLibraries.impl;

import com.myLibraries.ModelMapper;
import com.myLibraries.ModelMapperType;

public class ModelMapperFactory {
    public static <S, D> ModelMapper<S, D> get(ModelMapperType type) {
        return switch (type) {
            case CLASS_WITH_DEFAULT_CONSTRUCTOR -> new SimpleModelMapper<>();
            case CLASS_WITH_ALL_ARGS_CONSTRUCTOR -> new AllArgsConstructorMapper<>();
            case RECORD -> new RecordMapper<>();
        };
    }

}
