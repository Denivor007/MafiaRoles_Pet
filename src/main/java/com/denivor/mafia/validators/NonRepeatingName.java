package com.denivor.mafia.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NonRepeatingNameValidator.class)
public @interface NonRepeatingName {
    String message() default "name shouldn`t ne repeated, its stupid";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
