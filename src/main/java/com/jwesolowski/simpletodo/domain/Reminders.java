package com.jwesolowski.simpletodo.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Reminders implements GenericEntity<Reminders> {

  private long id;
  private LocalDateTime due;
  private List<LocalDateTime> reminders;

  @Override
  public long getId() {
    return id;
  }
}
