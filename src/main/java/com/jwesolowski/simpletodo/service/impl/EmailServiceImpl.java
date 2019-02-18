package com.jwesolowski.simpletodo.service.impl;

import com.google.common.collect.Lists;
import com.jwesolowski.simpletodo.domain.Task;
import com.jwesolowski.simpletodo.domain.User;
import com.jwesolowski.simpletodo.repository.TaskRepository;
import com.jwesolowski.simpletodo.repository.UserRepository;
import com.jwesolowski.simpletodo.service.EmailService;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

  @Autowired
  private it.ozimov.springboot.mail.service.EmailService emailService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TaskRepository taskRepository;

  @Transactional
  public void sendDailyMessage() throws AddressException {

    if (userRepository.findAll().stream().anyMatch(User::getSendDaily)) {

      for (User user : userRepository.findAll().stream().filter(User::getSendDaily)
          .collect(Collectors.toList())) {

        List<Task> todayTasks =
            user
                .getProjects()
                .stream()
                .flatMap(e -> e.getTasks().stream())
                .filter(task -> Objects.nonNull(task.getReminders().get(0).getDate()))
                .filter(task -> task.getReminders().get(0).getDate().equals(LocalDate.now()))
                .collect(Collectors.toList());

        String body;

        if (todayTasks.size() == 0) {
          body = "You are all set for today!\nHave a nice day!";
        } else {

          StringWriter stringWriter = new StringWriter();
          PrintWriter writer = new PrintWriter(stringWriter, true);
          writer.println("Tasks for today!");

          for (Task t : todayTasks) {
            writer.println(t.getName());
          }
          writer.println("Good luck and have a nice day!");

          body = stringWriter.toString();
        }


        final Email email = DefaultEmail.builder()
            .from(new InternetAddress("simpletodopz@gmail.com"))
            .to(Lists.newArrayList(new InternetAddress(user.getEmail())))
            .subject("SimpleTodo daily")
            .body(body)
            .encoding(String.valueOf(Charset.forName("UTF-8"))).build();

        emailService.send(email);
      }
    }
  }

  @Override
  public boolean setDailyMessages(String username) {
    User user = userRepository.getByUsername(username);
    user.setSendDaily(true);
    return userRepository.save(user).getSendDaily();
  }

  @Override
  public boolean removeDailyMessages(String username) {
    User user = userRepository.getByUsername(username);
    user.setSendDaily(false);
    return userRepository.save(user).getSendDaily();
  }


  @Override
  public boolean getDaily(String username) {
    User user = userRepository.getByUsername(username);
    return user.getSendDaily();
  }

  @Override
  public void sendRegisterEmail() {

  }

  @Scheduled(cron = "${dailyTime}")
  @Transactional
  public void scheduleDaily() throws AddressException {
    sendDailyMessage();
  }
}
