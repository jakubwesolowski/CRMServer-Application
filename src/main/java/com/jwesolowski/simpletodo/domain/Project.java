package com.jwesolowski.simpletodo.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

  @OneToMany(mappedBy = "project")
  private List<Task> tasks = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;


  @Override
  public long getId() {
    return id;
  }
}
