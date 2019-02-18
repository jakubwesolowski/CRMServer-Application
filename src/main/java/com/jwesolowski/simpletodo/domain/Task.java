package com.jwesolowski.simpletodo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "Task")
@Table(name = "task")
public class Task implements GenericEntity<Task> {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
  @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
  private Long id;

  @Column(name = "description")
  private String description;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "task", orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Reminder> reminders = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  @Column(name = "priority")
  private Priority priority;

  @Column(name = "completed")
  @NotNull
  private boolean completed;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id")
  @JsonBackReference
  private Project project;

  @Override
  public Long getId() {
    return id;
  }

  public void addReminder(Reminder reminder) {
    reminders.add(reminder);
    reminder.setTask(this);
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

  public void removeReminder(Reminder reminder) {
    this.reminders.remove(reminder);
    reminder.setTask(null);
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

  @Override
  public int hashCode() {
    return 32;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Reminder)) {
      return false;
    }
    return id != null && id.equals(((Task) o).id);
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }
}
