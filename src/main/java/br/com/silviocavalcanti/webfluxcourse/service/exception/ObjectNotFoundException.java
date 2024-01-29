package br.com.silviocavalcanti.webfluxcourse.service.exception;

public class ObjectNotFoundException extends RuntimeException {
  public ObjectNotFoundException(String id, String type){
    super(String.format("Object Not Found. Id: %s, Type: %s", id, type));
  }
}
