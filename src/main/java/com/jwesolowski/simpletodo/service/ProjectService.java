package com.jwesolowski.simpletodo.service;

import com.jwesolowski.simpletodo.domain.Project;
import java.util.List;

public interface ProjectService {

  Project addProject(Project project);

  List<Project> getProjectByUsername(String username);

}
