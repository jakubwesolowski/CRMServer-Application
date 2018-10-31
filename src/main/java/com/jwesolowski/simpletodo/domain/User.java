package com.jwesolowski.simpletodo.domain;

public class User implements GenericEntity<User> {

  private long id;

  @Override
  public long getId() {
    return id;
  }
}
