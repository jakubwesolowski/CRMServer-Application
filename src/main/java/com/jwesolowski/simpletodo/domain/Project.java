package com.jwesolowski.simpletodo.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT")
public class Project implements GenericEntity<Project> {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECT_SEQ")
  @SequenceGenerator(name = "PROJECT_SEQ", sequenceName = "PROJECT_SEQ", allocationSize = 1)
  private Long id;

  @OneToMany(mappedBy = "projectId", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Task> tasks = new ArrayList<>();

  @Column(name = "PROJECT_NAME")
  private String name;

  @Column(name = "USER_ID")
  private Long userId;

  @Override
  public Long getId() {
    return id;
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public String getName() {
    return name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
