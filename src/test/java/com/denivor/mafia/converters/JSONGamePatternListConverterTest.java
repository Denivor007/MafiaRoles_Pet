package com.denivor.mafia.converters;

import com.denivor.mafia.models.GamePatternList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(JSONGamePatternListConverter.class)
class JSONGamePatternListConverterTest {
    @Autowired
    JSONGamePatternListConverter jsonGamePatternConverter;
    @Test
    public void global(){
        GamePatternList gamePattern = new GamePatternList();
        String res = jsonGamePatternConverter.convertToDatabaseColumn(gamePattern);
        System.out.println(res);
        GamePatternList current = jsonGamePatternConverter.convertToEntityAttribute(res);
        Assertions.assertEquals(gamePattern,current);
    }


}