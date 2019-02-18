package com.jwesolowski.simpletodo.service;

import com.jwesolowski.simpletodo.domain.User;
import javax.mail.internet.AddressException;

public interface EmailService {

  boolean removeDailyMessages(String username);

  boolean setDailyMessages(String username);

  boolean getDaily(String name);

  void sendRegisterEmail(User user) throws AddressException;
}
