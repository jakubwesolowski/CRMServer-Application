package com.jwesolowski.simpletodo.domain;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "TASK")
public class Task implements GenericEntity<Task> {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAKS_SEQ")
  @SequenceGenerator(name = "TAKS_SEQ", sequenceName = "TAKS_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "TASK_NAME")
  private String taksName;

  @OneToOne(mappedBy = "REMINDERS", cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
  private Reminders reminders;

  @Enumerated(EnumType.STRING)
  @Column(name = "PRIORITY")
  private Priority priority;

  @Column(name = "COMPLATED")
  @NotNull
  private boolean completed;

  @Override
  public long getId() {
    return id;
  }
}
