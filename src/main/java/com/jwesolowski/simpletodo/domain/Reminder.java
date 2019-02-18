package com.jwesolowski.simpletodo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Reminder")
@Table(name = "reminder")
public class Reminder implements GenericEntity<Reminder> {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reminder_seq")
  @SequenceGenerator(name = "reminder_seq", sequenceName = "reminder_seq", allocationSize = 1)
  private Long id;

  @Column(name = "alarm")
  private LocalTime alarm;

  @Column(name = "date")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonFormat(shape = Shape.STRING, pattern = "yyyy/MM/dd")
  private LocalDate date;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "task_id")
  @JsonBackReference
  private Task task;

  @Override
  public Long getId() {
    return id;
  }

  public LocalDate getDate() {
    return date;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Reminder)) {
      return false;
    }
    return id != null && id.equals(((Reminder) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
