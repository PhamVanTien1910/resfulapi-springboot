package com.springboot.converter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringSetConverter implements AttributeConverter<Set<String>, String> {

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(Set<String> set) {
        return set != null ? String.join(SEPARATOR, set) : null;
    }

    @Override
    public Set<String> convertToEntityAttribute(String joined) {
        return joined != null ? Arrays.stream(joined.split(SEPARATOR)).collect(Collectors.toSet()) : new HashSet<>();
    }
}
