package br.com.silviocavalcanti.webfluxcourse.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TrimStringValidator implements ConstraintValidator<TrimString, String>{

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value.equals(null) || value.trim().length() == value.length();
  }
  
}
