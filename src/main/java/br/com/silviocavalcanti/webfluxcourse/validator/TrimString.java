package br.com.silviocavalcanti.webfluxcourse.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = { TrimStringValidator.class })
@Target(FIELD)
@Retention(RUNTIME)
public @interface TrimString {

  String message() default "The field must not contain blank spaces at the beginning or end.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
