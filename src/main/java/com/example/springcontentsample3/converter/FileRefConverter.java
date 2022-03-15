package com.example.springcontentsample3.converter;

import com.example.springcontentsample3.entity.FileRef;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FileRefConverter implements AttributeConverter<FileRef, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(FileRef fsFileRef) {
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
    public FileRef convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        try {
            return objectMapper.readValue(s, FileRef.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
