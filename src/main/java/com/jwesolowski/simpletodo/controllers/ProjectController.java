package com.jwesolowski.simpletodo.controllers;

import com.jwesolowski.simpletodo.domain.Project;
import com.jwesolowski.simpletodo.domain.User;
import com.jwesolowski.simpletodo.service.ProjectService;
import com.jwesolowski.simpletodo.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class ProjectController {

  @Autowired
  private ProjectService projectService;

  @Autowired
  private UserService userService;

  @RequestMapping("/project/add")
  public Project addProject(@RequestBody Project project) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    User user = userService.getUserFromUsername(authentication.getName());
//    project.setUser(user);
    return projectService.addProject(project);
  }

  @RequestMapping("/project/all")
  public List<Project> addProject() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return projectService.getProjectByUsername(authentication.getName());
  }

  @RequestMapping("/project/update")
  public Project updateProject(@RequestBody Project project) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    User user = userService.getUserFromUsername(authentication.getName());
//    project.setUser(user);
    return projectService.updateProject(project);
  }
}
