package com.jwesolowski.simpletodo.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name = "REMINDERS")
public class Reminders implements GenericEntity<Reminders> {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REMINDERS_SEQ")
  @SequenceGenerator(name = "REMINDERS_SEQ", sequenceName = "REMINDERS_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "DUE")
  private LocalDateTime due;

  @Column(name = "ALARMS")
  @OneToMany(fetch = FetchType.LAZY)
  private List<LocalDateTime> alarms;

  @Override
  public long getId() {
    return id;
  }
}
