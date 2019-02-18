package com.jwesolowski.simpletodo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity(name = "Project")
@Table(name = "project")
public class Project implements GenericEntity<Project> {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
  @SequenceGenerator(name = "project_seq", sequenceName = "project_seq", allocationSize = 1)
  private Long id;

  @OneToMany(mappedBy = "project", orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Task> tasks = new ArrayList<>();

  @Column(name = "name")
  private String name;

  @Column(name = "removed")
  @NotNull
  private Boolean removed = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private User user;

  @Override
  public Long getId() {
    return id;
  }

  public void addTask(Task task) {
    tasks.add(task);
    task.setProject(this);
  }

  public void removeTask(Task task) {
    tasks.remove(task);
    task.setProject(null);
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

  @Override
  public int hashCode() {
    return 33;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Reminder)) {
      return false;
    }
    return id != null && id.equals(((Project) o).id);
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Boolean getRemoved() {
    return removed;
  }

  public void setRemoved(Boolean removed) {
    this.removed = removed;
  }
}
