package com.jwesolowski.simpletodo.repository;

import com.jwesolowski.simpletodo.domain.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

}
