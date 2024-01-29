package br.com.silviocavalcanti.webfluxcourse.controller.exception;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

@Getter
public class ValidationError extends StandardError {

  @Serial
  private static final long serialVersionUID = 1L;

  private List<FieldError> errors = new ArrayList<>();

  public ValidationError(LocalDateTime timestamp, String path, Integer status, String error, String message) {
    super(timestamp, path, status, error, message);
  }

  public void addError(String field, String message) {
    errors.stream()
    .filter((e) -> field.equalsIgnoreCase(e.getField()))
    .findFirst()
    .ifPresentOrElse(
      (fieldError) -> fieldError.addMessage(message), 
      () -> errors.add(new FieldError(field, message))
    );
  }

  private static final class FieldError {
    
    private final String field;
    private final List<String> caused = new ArrayList<>();

    public FieldError(String field, String message) {
      this.field = field;
      this.caused.add(message);
    }
    
    public String getField(){
      return field;
    }

    public void addMessage(String msg){
      this.caused.add(msg);
    }

    @SuppressWarnings("unused")
    public List<String> getCaused(){
      return Collections.unmodifiableList(caused);
    }

  }
}
