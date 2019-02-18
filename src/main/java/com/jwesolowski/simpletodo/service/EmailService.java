package com.jwesolowski.simpletodo.service;

import java.io.UnsupportedEncodingException;
import javax.mail.internet.AddressException;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

  boolean removeDailyMessages(String username) throws UnsupportedEncodingException, AddressException;

  boolean setDailyMessages(String username) throws UnsupportedEncodingException, AddressException;

  boolean getDaily(String name);

  void sendRegisterEmail();
}
