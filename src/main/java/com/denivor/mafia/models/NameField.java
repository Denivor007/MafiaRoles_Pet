package com.denivor.mafia.models;

import com.denivor.mafia.validators.NonRepeatingName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class NameField {
    @NotEmpty(message = "name must not be empty")
    @Size(min = 2, max = 30, message = "name length must be between 2 and 30 characters")
    @NonRepeatingName
    private String value;

    public NameField (){

    }

    public NameField (String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
