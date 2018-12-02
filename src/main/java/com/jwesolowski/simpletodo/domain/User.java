package com.jwesolowski.simpletodo.domain;

public class User implements GenericEntity<User> {

  private long id;
  private String name;
  private String email;

  @Override
  public long getId() {
    return id;
  }
}
