package com.jwesolowski.simpletodo.service.impl;

import com.jwesolowski.simpletodo.domain.Reminder;
import com.jwesolowski.simpletodo.repository.ReminderRepository;
import com.jwesolowski.simpletodo.service.ReminderService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReminderServiceImpl implements ReminderService {

  @Autowired
  private ReminderRepository reminderRepository;

  @Override
  public List<Reminder> getTaskReminders(Long taskId) {
    reminderRepository.findAll().stream().map(Reminder::getTaskId)
        .forEach(System.out::println);

    return reminderRepository.findAll().stream()
        .filter(reminder -> reminder.getTaskId().equals(taskId))
        .collect(Collectors.toList());
  }
}
