package com.jwesolowski.simpletodo.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "REMINDERS")
public class Reminder implements GenericEntity<Reminder> {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REMINDER_SEQ")
  @SequenceGenerator(name = "REMINDER_SEQ", sequenceName = "REMINDER_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "ALARM")
  private LocalDateTime alarm;

  @ManyToOne
  @JoinColumn(name = "task_id")
  private Task task;

  @Override
  public long getId() {
    return id;
  }
}
