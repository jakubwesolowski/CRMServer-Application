package com.jwesolowski.simpletodo.security.service;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

  private static final long serialVersionUID = 1250166508152483573L;

  private final String token;
  private final String username;

  public JwtAuthenticationResponse(String username, String token) {
    this.username = username;
    this.token = token;
  }

  public String getToken() {
    return this.token;
  }

  public String getUsername() {
    return username;
  }
}
