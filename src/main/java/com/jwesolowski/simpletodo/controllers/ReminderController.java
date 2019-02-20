package com.jwesolowski.simpletodo.controllers;

import com.jwesolowski.simpletodo.domain.Reminder;
import com.jwesolowski.simpletodo.service.ReminderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://51.145.158.148:4200", maxAge = 3600)
@RestController
public class ReminderController {

  @Autowired
  private ReminderService reminderService;

  @RequestMapping("/task/{taskId}/reminders")
  public List<Reminder> getTaskReminders(@PathVariable Long taskId) {
    return reminderService.getTaskReminders(taskId);
  }

}
