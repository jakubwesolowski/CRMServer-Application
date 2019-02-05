package com.jwesolowski.simpletodo.repository;

import com.jwesolowski.simpletodo.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
