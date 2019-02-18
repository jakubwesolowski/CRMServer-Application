package com.jwesolowski.simpletodo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
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

@Entity(name = "User")
@Table(name = "user")
public class User implements GenericEntity<User> {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
  @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
  private Long id;

  @Column(name = "username", length = 50, unique = true)
  @NotNull
  @Size(min = 4, max = 50)
  private String username;

  @Column(name = "password", length = 100)
  @NotNull
  @Size(min = 4, max = 100)
  private String password;

  @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Project> projects = new ArrayList<>();

  @Column(name = "enabled")
  @NotNull
  private Boolean enabled;

  @Column(name = "lastpasswordresetdate")
  @Temporal(TemporalType.TIMESTAMP)
  @NotNull
  private Date lastPasswordResetDate;

  @Column(name = "firstname", length = 50)
  @NotNull
  @Size(min = 2, max = 50)
  private String firstname;

  @Column(name = "lastname", length = 50)
  @NotNull
  @Size(min = 2, max = 50)
  private String lastname;

  @Column(name = "email", length = 50)
  @NotNull
  @Size(min = 4, max = 50)
  private String email;

  @Column(name = "daily", length = 50)
  private Boolean sendDaily = false;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "USER_AUTHORITY",
      joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
      inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
  private Set<Authority> authorities = new HashSet<>();

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
  public Long getId() {
    return id;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public void addProject(Project project) {
    projects.add(project);
    project.setUser(this);
  }

  public void removeProject(Project project) {
    projects.remove(project);
    project.setUser(null);
  }

  public Boolean getSendDaily() {
    return sendDaily;
  }

  public void setSendDaily(Boolean sendDaily) {
    this.sendDaily = sendDaily;
  }
}
