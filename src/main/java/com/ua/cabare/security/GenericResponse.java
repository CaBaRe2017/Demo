package com.ua.cabare.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;

public class GenericResponse {

  private String message;
  private String error;

  public GenericResponse(String message) {
    this.message = message;
  }

  public GenericResponse(String message, String error) {
    this.message = message;
    this.error = error;
  }

  public GenericResponse(List<ObjectError> allErrors, String error) {
    this.error = error;
    this.message = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(
        Collectors.joining(","));
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}
