package com.jwesolowski.simpletodo.service.impl;

import com.jwesolowski.simpletodo.domain.Project;
import com.jwesolowski.simpletodo.domain.Reminder;
import com.jwesolowski.simpletodo.domain.Task;
import com.jwesolowski.simpletodo.domain.User;
import com.jwesolowski.simpletodo.repository.ProjectRepository;
import com.jwesolowski.simpletodo.repository.ReminderRepository;
import com.jwesolowski.simpletodo.repository.TaskRepository;
import com.jwesolowski.simpletodo.repository.UserRepository;
import com.jwesolowski.simpletodo.service.TaskService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private ReminderRepository reminderRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ProjectRepository projectRepository;

  @Override
  public List<Task> getAllTaskByUsername(String username) {

    return userRepository.getByUsername(username)
        .getProjects()
        .stream()
        .flatMap(e -> e.getTasks().stream())
        .collect(Collectors.toList());
  }

  @Override
  public List<Task> getTodayTasksByUsername(String username) {

    return userRepository.getByUsername(username)
        .getProjects()
        .stream()
        .flatMap(e -> e.getTasks().stream())
        .filter(task -> task.getReminders().get(0).getDate().equals(LocalDate.now()))
        .collect(Collectors.toList());
  }

  @Override
  public List<Task> getInboxTasksByUsername(String username) {

    return userRepository.getByUsername(username)
        .getProjects()
        .stream()
        .filter(project -> project.getName().equals("Inbox"))
        .flatMap(e -> e.getTasks().stream())
        .collect(Collectors.toList());
  }

  @Override
  public Task addTaskToProject(Task task, Long projectId) {

    Optional<Project> maybeProject = projectRepository.findById(projectId);

    if (maybeProject.isPresent()) {
      Project project = maybeProject.get();
      task.setProject(project);
      Task t = taskRepository.saveAndFlush(task);

      Reminder reminder = new Reminder();
      reminder.setTask(t);
      reminderRepository.saveAndFlush(reminder);
      return taskRepository.saveAndFlush(task);

    } else {
//      return addTask(task);
      return null;
    }
  }

  @Override
  public Task addTask(Task task) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.getByUsername(authentication.getName());

    Project project = user.getProjects().stream()
        .filter(p -> p.getName().equals("Inbox")).findAny().get();

    task.setProject(project);
    Task t = taskRepository.saveAndFlush(task);

    Reminder reminder = new Reminder();
    reminder.setTask(t);
    reminderRepository.saveAndFlush(reminder);
    return taskRepository.saveAndFlush(task);

  }

  @Override
  public Task updateTask(Task task, Long projectId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.getByUsername(authentication.getName());

//    Reminder r = reminderRepository.findAll().stream()
//        .filter(reminder -> reminder.getTask().getId().equals(task.getId())).findAny().get();

    Project p = projectRepository.findById(projectId).get();

    p.removeTask(task);
    Project p1 = projectRepository.save(p);
    Task t1 = taskRepository.save(task);
    p1.addTask(t1);
    Project p2 = projectRepository.save(p1);
    t1.setProject(p2);
    return taskRepository.save(t1);

  }
}
