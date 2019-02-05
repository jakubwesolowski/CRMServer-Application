package com.jwesolowski.simpletodo.service.impl;

import com.jwesolowski.simpletodo.domain.User;
import com.jwesolowski.simpletodo.repository.UserRepository;
import com.jwesolowski.simpletodo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User getUserFromUsername(String username) {
    return userRepository.getByUsername(username);
  }

  @Override
  public void update(User user) {
    userRepository.save(user);
  }
}
