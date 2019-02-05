package com.jwesolowski.simpletodo.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

  @Column(name = "NAME")
  private String name;

  @OneToMany(mappedBy = "taskId", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Reminder> reminders = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  @Column(name = "PRIORITY")
  private Priority priority;

  @Column(name = "COMPLETED")
  @NotNull
  private boolean completed;

  @Column(name = "PROJECT_ID")
  private Long projectId;

  @Override
  public Long getId() {
    return id;
  }

  public void addReminder(Reminder reminder) {
    reminders.add(reminder);
  }

  public String getDescription() {
    return description;
  }

  public List<Reminder> getReminders() {
    return reminders;
  }

  public Priority getPriority() {
    return priority;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setReminders(List<Reminder> reminders) {
    this.reminders = reminders;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }
}
