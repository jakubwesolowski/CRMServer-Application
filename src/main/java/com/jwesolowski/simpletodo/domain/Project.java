package com.jwesolowski.simpletodo.domain;

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

@Entity(name = "PROJECT")
public class Project implements GenericEntity<Project> {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECT_SEQ")
  @SequenceGenerator(name = "PROJECT_SEQ", sequenceName = "PROJECT_SEQ", allocationSize = 1)
  private Long id;

  @OneToMany(mappedBy = "TASK", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Task> tasks;

  @OneToMany(mappedBy = "USER", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private User user;

  @Override
  public long getId() {
    return id;
  }
}
