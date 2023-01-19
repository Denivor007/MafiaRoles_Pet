package com.denivor.mafia.converters;

import com.denivor.mafia.models.GamePattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(JSONGamePatternConverter.class)
class JSONGamePatternConverterTest {

    @Autowired
    JSONGamePatternConverter jsonGamePatternConverter;
    @Test
    public void global(){
        GamePattern gamePattern = new GamePattern();
        String res = jsonGamePatternConverter.convertToDatabaseColumn(gamePattern);
        GamePattern current = jsonGamePatternConverter.convertToEntityAttribute(res);
        Assertions.assertEquals(gamePattern,current);
    }

}