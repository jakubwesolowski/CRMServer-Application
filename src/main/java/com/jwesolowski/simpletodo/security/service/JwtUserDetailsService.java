package com.jwesolowski.simpletodo.security.service;

import com.jwesolowski.simpletodo.domain.User;
import com.jwesolowski.simpletodo.repository.UserRepository;
import com.jwesolowski.simpletodo.security.JwtUserFactory;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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

    User user = StreamSupport.stream(userRepository.findAll().spliterator(), false)
        .filter(u -> u.getUsername().equals(username)).collect(Collectors.toList()).get(0);

    if (user == null) {
      throw new UsernameNotFoundException(
          String.format("No user found with username '%s'.", username));
    } else {
      return JwtUserFactory.create(user);
    }
  }
}
