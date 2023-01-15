package com.denivor.mafia.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JSONListConverter implements AttributeConverter<List<String>, String> {
    private ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<String> customerInfo) {
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(customerInfo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return customerInfoJson;
    }

    @Override
    public List<String> convertToEntityAttribute(String customerInfoJSON) {
        List<String> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(customerInfoJSON,
                    new TypeReference<List<String>>() {
                    });
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return customerInfo;
    }
}
