package com.jwesolowski.simpletodo.service;

import com.jwesolowski.simpletodo.domain.Task;
import java.util.List;

public interface TaskService {

  List<Task> getAllTaskByUsername(String username);

  List<Task> getTodayTasksByUsername(String username);

  List<Task> getInboxTasksByUsername(String username);

  Task addTaskToProject(Task task, Long projectName);

  Task addTask(Task task);

  boolean completeTask(Long taskId);

  Task updateTask(Task task);
}
