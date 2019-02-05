package com.jwesolowski.simpletodo.service;

import com.jwesolowski.simpletodo.domain.Reminder;
import java.util.Collection;
import java.util.List;

public interface ReminderService {

  List<Reminder> getTaskReminders(Long taskId);

}
