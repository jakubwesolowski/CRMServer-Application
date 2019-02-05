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
  private UserRepository userRepository;

  @Autowired
  private ReminderRepository reminderRepository;

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
//        .filter(task -> task.getReminders().get(0).getAlarm().toLocalDate().equals(LocalDate.now()))
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

    task.addReminder(newReminder(task.getId()));
    task.setProjectId(projectId);
    return taskRepository.save(task);
  }

  @Override
  public Task addTask(Task task) {

    Optional<Project> maybeInbox = projectRepository.findAll().stream()
        .filter(project -> project.getName().equals("Inbox")).findAny();

    if (maybeInbox.isPresent()) {
      task.setProjectId(maybeInbox.get().getId());

    } else {

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      User user = userRepository.getByUsername(authentication.getName());

      Project newInbox = new Project();
      newInbox.setUserId(user.getId());
      newInbox.setName("Inbox");
      Long newInboxId = projectRepository.save(newInbox).getId();

      task.setProjectId(newInboxId);
    }

    task.addReminder(newReminder(task.getId()));
    System.out.print("reminders in addTask Service  ");
    task.getReminders().stream().map(Reminder::getTaskId).forEach(System.out::println);

    return taskRepository.save(task);
  }

  @Override
  public boolean completeTask(Long taskId) {

    Optional<Task> maybeTask = taskRepository.findById(taskId);

    if (maybeTask.isPresent()) {
      Task task = maybeTask.get();
      task.setCompleted(true);
      taskRepository.save(task);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Task updateTask(Task task) {
    return taskRepository.save(task);
  }

  public Reminder newReminder(Long taskId) {

    Reminder newReminder = new Reminder();
    newReminder.setTaskId(taskId);
    System.out.println("Before orm " + newReminder.getTaskId());
    Reminder fromOrm = reminderRepository.save(newReminder);
    System.out.println("From orm task id " + fromOrm.getTaskId());
    return fromOrm;
  }
}
