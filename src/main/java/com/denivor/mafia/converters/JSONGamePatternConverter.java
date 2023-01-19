package com.denivor.mafia.converters;

import com.denivor.mafia.models.GamePattern;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter
public class JSONGamePatternConverter implements AttributeConverter<GamePattern, String> {
    @Autowired
    public ObjectMapper objectMapper;
    @Override
    public String convertToDatabaseColumn(GamePattern customerInfo) {
       String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(customerInfo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return customerInfoJson;
    }

    @Override
    public GamePattern convertToEntityAttribute(String customerInfoJSON) {
        GamePattern customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(customerInfoJSON,
                    new TypeReference<GamePattern>() {
                    });
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return customerInfo;
    }
}
