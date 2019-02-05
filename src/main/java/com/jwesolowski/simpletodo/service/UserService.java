package com.jwesolowski.simpletodo.service;

import com.jwesolowski.simpletodo.domain.User;

public interface UserService {

  User getUserFromUsername(String username);

  void update(User user);
}
