package com.example.springcontentsample2.converter;

import com.example.springcontentsample2.entity.FsFileRef;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FsFileRefConverter implements AttributeConverter<FsFileRef, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(FsFileRef fsFileRef) {
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
    public FsFileRef convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        try {
            return objectMapper.readValue(s, FsFileRef.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
