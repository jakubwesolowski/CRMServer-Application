package com.jwesolowski.simpletodo.repository;

import com.jwesolowski.simpletodo.domain.User;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByUsername(String username);

  User getByUsername(String username);

}
