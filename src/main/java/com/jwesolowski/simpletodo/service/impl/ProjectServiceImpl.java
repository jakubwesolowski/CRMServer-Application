package com.jwesolowski.simpletodo.service.impl;

import com.jwesolowski.simpletodo.domain.Project;
import com.jwesolowski.simpletodo.repository.ProjectRepository;
import com.jwesolowski.simpletodo.repository.UserRepository;
import com.jwesolowski.simpletodo.service.ProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public Project addProject(Project project) {
    System.out.println("ProjectService; UserID=" + project.getUserId());
    return projectRepository.save(project);
  }

  @Override
  public List<Project> getProjectByUsername(String username) {

    return userRepository.getByUsername(username).getProjects();

//    return projectRepository.findAll().stream()
//        .filter(project -> project.getUser().getUsername().equals(username)).collect(
//            Collectors.toList());
  }
}
