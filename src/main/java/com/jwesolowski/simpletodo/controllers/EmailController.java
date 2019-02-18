package com.jwesolowski.simpletodo.controllers;

import com.jwesolowski.simpletodo.service.EmailService;
import java.io.UnsupportedEncodingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class EmailController {

  @Autowired
  private EmailService emailService;

  @RequestMapping("/email/send/getDaily")
  public boolean getDaily() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return emailService.getDaily(authentication.getName());
  }

  @RequestMapping("/email/send/setDaily")
  public boolean setDaily() throws UnsupportedEncodingException, AddressException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return emailService.setDailyMessages(authentication.getName());
  }

  @RequestMapping("/email/send/removeDaily")
  public boolean removeDaily() throws UnsupportedEncodingException, AddressException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return emailService.removeDailyMessages(authentication.getName());
  }
}
