package com.jwesolowski.simpletodo.controllers;

import com.jwesolowski.simpletodo.domain.Task;
import com.jwesolowski.simpletodo.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

  @Autowired
  private TaskService taskService;

  @RequestMapping("/task/all")
  public List<Task> getTasks() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return taskService.getAllTaskByUsername(authentication.getName());
  }

  @RequestMapping("/task/inbox")
  public List<Task> getInboxTasks() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return taskService.getInboxTasksByUsername(authentication.getName());
  }

  @RequestMapping("/task/today")
  public List<Task> getTodayTasks() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return taskService.getTodayTasksByUsername(authentication.getName());
  }

  @RequestMapping("/project/{projectId}/addTask")
  public Task addTaskToProject(@RequestBody Task task, @PathVariable Long projectId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return taskService.addTaskToProject(task, projectId);
  }

  @RequestMapping("/task/add")
  public ResponseEntity<Task> addTask(@RequestBody Task task) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return new ResponseEntity<>(taskService.addTask(task), HttpStatus.OK);
  }

  @RequestMapping("/project/{projectId}/task/update")
  public Task updateTask(@RequestBody Task task, @PathVariable Long projectId) {
    return taskService.updateTask(task, projectId);
  }

}
