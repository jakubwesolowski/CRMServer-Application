package com.jwesolowski.simpletodo.security.controller;

class AuthenticationException extends RuntimeException {

  public AuthenticationException(String message, Throwable cause) {
    super(message, cause);
  }
}
