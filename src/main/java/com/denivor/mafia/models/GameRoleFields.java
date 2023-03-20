package com.denivor.mafia.models;

import com.denivor.mafia.validators.NonRepeatingRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class GameRoleFields {
    @NotEmpty(message = "name must not be empty")
    @Size(min = 2, max = 30, message = "name length must be between 2 and 30 characters")
    @NonRepeatingRole
    String key;

    @Max(10)
    @Min(1)
    Integer value;
}
