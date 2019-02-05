package com.jwesolowski.simpletodo.repository;

import com.jwesolowski.simpletodo.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

  Project findByName(String name);

}
