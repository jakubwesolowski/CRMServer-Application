package com.jwesolowski.simpletodo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class User implements GenericEntity<User> {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
  @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "USERNAME", length = 50, unique = true)
  @NotNull
  @Size(min = 4, max = 50)
  private String username;

  @Column(name = "PASSWORD", length = 100)
  @NotNull
  @Size(min = 4, max = 100)
  private String password;

  @OneToMany(mappedBy = "user")
  private List<Project> tasks = new ArrayList<>();

  @Column(name = "ENABLED")
  @NotNull
  private Boolean enabled;

  @Column(name = "LASTPASSWORDRESETDATE")
  @Temporal(TemporalType.TIMESTAMP)
  @NotNull
  private Date lastPasswordResetDate;

  @Column(name = "FIRSTNAME", length = 50)
  @NotNull
  @Size(min = 4, max = 50)
  private String firstname;

  @Column(name = "LASTNAME", length = 50)
  @NotNull
  @Size(min = 4, max = 50)
  private String lastname;

  @Column(name = "EMAIL", length = 50)
  @NotNull
  @Size(min = 4, max = 50)
  private String email;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "USER_AUTHORITY",
      joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
      inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
  private Set<Authority> authorities;

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Project> getTasks() {
    return tasks;
  }

  public void setTasks(List<Project> tasks) {
    this.tasks = tasks;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Date getLastPasswordResetDate() {
    return lastPasswordResetDate;
  }

  public void setLastPasswordResetDate(Date lastPasswordResetDate) {
    this.lastPasswordResetDate = lastPasswordResetDate;
  }

  public Set<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<Authority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public long getId() {
    return id;
  }
}
