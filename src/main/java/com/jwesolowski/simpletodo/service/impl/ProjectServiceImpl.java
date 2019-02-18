package com.jwesolowski.simpletodo.service.impl;

import com.jwesolowski.simpletodo.domain.Project;
import com.jwesolowski.simpletodo.domain.User;
import com.jwesolowski.simpletodo.repository.ProjectRepository;
import com.jwesolowski.simpletodo.repository.UserRepository;
import com.jwesolowski.simpletodo.service.ProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public Project addProject(Project project) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.getByUsername(authentication.getName());
    Project p = projectRepository.saveAndFlush(project);
    p.setUser(user);
    return projectRepository.save(project);
  }

  @Override
  public List<Project> getProjectByUsername(String username) {
    return userRepository.getByUsername(username).getProjects();
  }

  @Override
  public Project updateProject(Project project) {
    return projectRepository.save(project);
  }
}
