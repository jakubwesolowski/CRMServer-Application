package com.jwesolowski.simpletodo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class TaskController {

  @RequestMapping("/tasks")
  public String getTasks() {
    return "Lol tasks";
  }

}
