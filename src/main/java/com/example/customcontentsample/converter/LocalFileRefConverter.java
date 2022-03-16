package com.example.customcontentsample.converter;

import com.example.customcontentsample.entity.LocalFileRef;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalFileRefConverter implements AttributeConverter<LocalFileRef, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(LocalFileRef fsFileRef) {
        if (fsFileRef == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(fsFileRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LocalFileRef convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        try {
            return objectMapper.readValue(s, LocalFileRef.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
