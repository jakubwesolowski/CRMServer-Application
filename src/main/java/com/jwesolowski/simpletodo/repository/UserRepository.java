package com.jwesolowski.simpletodo.repository;

import com.jwesolowski.simpletodo.domain.User;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
