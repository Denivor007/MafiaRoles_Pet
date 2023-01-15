package com.denivor.mafia.validators;

import com.denivor.mafia.models.GamePattern;
import com.denivor.mafia.models.PlayersList;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

public class NonRepeatingNameValidator implements ConstraintValidator<NonRepeatingName, String> {
    @Autowired
    PlayersList playersList;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        ArrayList<String>  players = (ArrayList<String>) playersList.getPlayers();
        return !players.contains(s);
    }
}
