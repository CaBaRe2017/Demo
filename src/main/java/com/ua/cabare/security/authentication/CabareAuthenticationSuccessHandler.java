package com.ua.cabare.security.authentication;

import com.ua.cabare.security.LoggedEmployees;
import com.ua.cabare.security.dto.ActiveEmployees;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CabareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private final Logger logger = LoggerFactory.getLogger(CabareAuthenticationSuccessHandler.class);

  @Autowired
  private ActiveEmployees activeEmployees;

  @Autowired
  private RedirectStrategy redirectStrategy;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    handle(request, response, authentication);
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.setMaxInactiveInterval(30 * 60); // need to insert correct time
      LoggedEmployees employees = new LoggedEmployees(authentication.getName(), activeEmployees);
      session.setAttribute("employees", employees);
    }
    clearAuthenticationAttributes(request);
  }

  private void clearAuthenticationAttributes(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return;
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }

  private void handle (HttpServletRequest request, HttpServletResponse response,
    Authentication authentication) throws IOException{
    String targetUrl = determineTargetUrl(authentication);
    if (response.isCommitted()) {
      logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
      return;
    }
    redirectStrategy.sendRedirect(request, response, targetUrl);
  }

  private String determineTargetUrl(Authentication authentication) {
    boolean isAdmin = false;
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    for (GrantedAuthority grantedAuthority: authorities) {
      if (grantedAuthority.getAuthority().equals("WRITE")) {
        isAdmin = true;
        break;
      }
    }
    if (isAdmin) {
      return "/admin.html?admin=" + authentication.getName();
    } else {
      return "/homepage.html";
    }
  }
}
