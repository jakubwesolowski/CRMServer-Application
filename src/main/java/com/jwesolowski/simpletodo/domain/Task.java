package com.jwesolowski.simpletodo.domain;

import java.time.LocalDateTime;

public class Task implements GenericEntity<Task> {

  private long id;
  private String description;
  private String name;
  private LocalDateTime due;
  private boolean completed;

  @Override
  public long getId() {
    return id;
  }
}
