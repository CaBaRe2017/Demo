package com.ua.cabare.security.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

  @GetMapping("/login")
  public String login(){
    return "login";
  }

  @GetMapping("/welcome")
  public String welcome(){
    return "welcome";
  }

  @GetMapping("/secured/welcome")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public String securedWelcome(){
    return "secured welcome";
  }

}
