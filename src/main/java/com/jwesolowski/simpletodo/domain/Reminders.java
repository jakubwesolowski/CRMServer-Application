package com.jwesolowski.simpletodo.domain;

public class Reminders implements GenericEntity<Reminders> {

  private long id;

  @Override
  public long getId() {
    return id;
  }
}
