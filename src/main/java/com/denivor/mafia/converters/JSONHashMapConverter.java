package com.denivor.mafia.converters;

import com.denivor.mafia.models.GamePattern;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

@Component
@Converter
public class JSONHashMapConverter implements AttributeConverter<Map<String, Integer>, String> {

    //@Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Integer> customerInfo) {

        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(customerInfo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return customerInfoJson;
    }

    @Override
    public Map<String, Integer> convertToEntityAttribute(String customerInfoJSON) {
        Map<String, Integer> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(customerInfoJSON,
                    new TypeReference<HashMap<String, Integer>>() {
                    });
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return customerInfo;
    }
}
