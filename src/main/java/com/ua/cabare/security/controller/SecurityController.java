package com.ua.cabare.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class SecurityController {

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(Model model, String error, String logout){
    if (error != null) {
      model.addAttribute("error", "Your username and password is invalid.");
    }

    if (logout != null) {
      model.addAttribute("message", "You have been logged out successfully.");
    }
    return "login";
  }

  /*@GetMapping("/welcome")
  public String welcome(){
    return "bill";
  }
*/
  @GetMapping("/secured/welcome")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public String securedWelcome(){
    return "redirect:/bill";
  }

}
