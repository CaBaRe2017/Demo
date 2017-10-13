package com.ua.cabare.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @PreAuthorize("hasAnyRole('ADMIN')")
  @RequestMapping(value = {"/secured/welcome","/admin/","secured/admin"})
  public String securedWelcome(){
    return "welcome admin";
  }

}
