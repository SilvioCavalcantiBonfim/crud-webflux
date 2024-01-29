package br.com.silviocavalcanti.webfluxcourse.model.request;

import br.com.silviocavalcanti.webfluxcourse.validator.TrimString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
  @NotBlank(message = "must not be null or empty") 
  @Size(min = 3, max = 50, message = "must be between 3 and 50 characters")
  @TrimString
  String name,
  
  @Email(message = "invalid email")
  @NotBlank(message = "must not be null or empty") 
  @TrimString
  String email,
  
  @Size(min = 8, max = 50, message = "must be between 8 and 50 characters")
  @TrimString
  String password  
) {}
