package com.example.customcontentsample.converter;

import com.example.customcontentsample.entity.LocalFileRef;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public class LocalFileRefListConverter implements AttributeConverter<List<LocalFileRef>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<LocalFileRef> fsFileRef) {
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
    public List<LocalFileRef> convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        try {
            return objectMapper.readValue(s, List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
