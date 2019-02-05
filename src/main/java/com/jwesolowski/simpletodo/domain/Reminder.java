package com.jwesolowski.simpletodo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "REMINDERS")
public class Reminder implements GenericEntity<Reminder> {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REMINDER_SEQ")
  @SequenceGenerator(name = "REMINDER_SEQ", sequenceName = "REMINDER_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "ALARM")
  private LocalTime alarm;

  @Column(name = "DATE")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonFormat(shape = Shape.STRING, pattern = "yyyy/MM/dd")
  private LocalDate date;

  @Column(name = "TASK_ID")
  private Long taskId;

  @Override
  public Long getId() {
    return id;
  }

  public LocalDate getDate() {
    return date;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }
}
