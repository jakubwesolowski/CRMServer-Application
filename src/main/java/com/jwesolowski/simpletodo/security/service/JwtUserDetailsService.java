package com.jwesolowski.simpletodo.security.service;

import com.jwesolowski.simpletodo.domain.User;
import com.jwesolowski.simpletodo.repository.UserRepository;
import com.jwesolowski.simpletodo.security.JwtUserFactory;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public JwtUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<User> maybeUser = userRepository.findAll().stream()
        .filter(u -> u.getUsername().equals(username)).findAny();

    if (maybeUser.isPresent()) {
      return JwtUserFactory.create(maybeUser.get());
    } else {
      throw new UsernameNotFoundException(
          String.format("No user found with username '%s'.", username));
    }
  }
}
