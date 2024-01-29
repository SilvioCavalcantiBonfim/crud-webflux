package br.com.silviocavalcanti.webfluxcourse.controller.exception;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import br.com.silviocavalcanti.webfluxcourse.service.exception.ObjectNotFoundException;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class ControllerExceptionHandle {

  @ExceptionHandler(DuplicateKeyException.class)
  ResponseEntity<Mono<StandardError>> duplicateKeyHandle(DuplicateKeyException exception, ServerHttpRequest request) {
    return ResponseEntity.status(BAD_REQUEST).body(
        Mono.just(StandardError.builder()
            .timestamp(now())
            .status(BAD_REQUEST.value())
            .error(BAD_REQUEST.getReasonPhrase())
            .message(verifyDupKey(exception.getMessage()))
            .path(request.getPath().toString())
            .build()));
  }

  @ExceptionHandler(WebExchangeBindException.class)
  ResponseEntity<Mono<ValidationError>> validationErrorHandle(WebExchangeBindException exception,
      ServerHttpRequest request) {
    ValidationError error = new ValidationError(now(), request.getPath().toString(), BAD_REQUEST.value(),
        "Validation Error", "Error on validation attributes");
    exception.getBindingResult().getFieldErrors().forEach((fieldError) -> error.addError(fieldError.getField(), fieldError.getDefaultMessage()));
    return ResponseEntity.badRequest().body(
      Mono.just(error)
    );
  }

  private String verifyDupKey(String msg) {
    if (msg.contains("email dup key")) {
      return "E-mail already registered";
    }
    return "Dup key exception";
  }


  @ExceptionHandler(ObjectNotFoundException.class)
  ResponseEntity<Mono<StandardError>> objectNotFoundHandle(ObjectNotFoundException exception, ServerHttpRequest request) {
    return ResponseEntity.status(NOT_FOUND).body(
        Mono.just(StandardError.builder()
            .timestamp(now())
            .status(NOT_FOUND.value())
            .error(NOT_FOUND.getReasonPhrase())
            .message(exception.getMessage())
            .path(request.getPath().toString())
            .build()));
  }
}
