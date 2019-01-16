package com.jwesolowski.simpletodo.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TASK")
public class Task implements GenericEntity<Task> {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_SEQ")
  @SequenceGenerator(name = "TASK_SEQ", sequenceName = "TASK_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "TASK_NAME")
  private String taksName;

  @OneToMany(mappedBy = "task")
  private List<Reminder> reminders = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  @Column(name = "PRIORITY")
  private Priority priority;

  @Column(name = "COMPLATED")
  @NotNull
  private boolean completed;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  @Override
  public long getId() {
    return id;
  }
}
