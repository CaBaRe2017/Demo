package com.ua.cabare.services.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class SecurityController {

  @RequestMapping(value = "/secured", method = RequestMethod.POST)
  public String login(Model model, String error, String logout){
    if (error != null) {
      model.addAttribute("error", "Your username and password is invalid.");
    }

    if (logout != null) {
      model.addAttribute("message", "You have been logged out successfully.");
    }
    return "welcome";
  }

  /*@GetMapping("/welcome")
  public String welcome(){
    return "bill";
  }
*/
  @PreAuthorize("hasAnyRole('ADMIN')")
  @RequestMapping(value = {"/secured/welcome","/admin/","/admin"})
  public String securedWelcome(){
    return "admin";
  }

}
