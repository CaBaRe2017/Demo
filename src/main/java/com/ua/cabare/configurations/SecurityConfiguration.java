package com.ua.cabare.configurations;

import com.ua.cabare.repositories.EmployeeRepository;
import com.ua.cabare.repositories.PrivilegeRepository;
import com.ua.cabare.repositories.RoleRepository;
import com.ua.cabare.security.authentication.CabareAuthenticationSuccessHandler;
import com.ua.cabare.security.dto.ActiveEmployees;
import com.ua.cabare.security.service.EmployeeDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = {EmployeeRepository.class, RoleRepository.class,
    PrivilegeRepository.class})
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private EmployeeDetailsService employeeDetailsService;


  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationSuccessHandler cabareAuthenticationSuccessHandler;


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(employeeDetailsService).passwordEncoder(passwordEncoder);
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/login*", "/logout*", "*/registration/employee*", "/registration*",
            "/registration/event*").permitAll()
        .antMatchers("/invalidSession*").anonymous()
        //.antMatchers("/employee/updatePassword*","/employee/savePassword*","/updatePassword*").hasAuthority("CHANGE_PASSWORD")
        //.anyRequest().hasAuthority("READ")
        //.and()
        /*.formLogin()
          //.loginPage("/login")
          .defaultSuccessUrl("/index.html")
          .failureUrl("/login?error=true")
          .successHandler(cabareAuthenticationSuccessHandler)*/
//        .permitAll()
        .and()
        .logout()
        .logoutUrl("/logout")
        .clearAuthentication(true)
        .logoutSuccessUrl("/login")
        .permitAll()
        .and()
        .httpBasic();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(11);
  }

  @Bean
  public ActiveEmployees activeEmployees() {
    return new ActiveEmployees();
  }

  @Bean
  public RedirectStrategy redirectStrategy() {
    return new DefaultRedirectStrategy();
  }

  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new CabareAuthenticationSuccessHandler();
  }


  /*@Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select e.login as username,"
        + " e.password, true as enabled from employee e"
        + " where e.login=?")
        .authoritiesByUsernameQuery("select e.login as username,"
            + " r.name as role from employee e"
            + " join employee_role er"
            + " join role r"
            + " on u.id=er.employee_id"
            + "and r.id=er.role_id"
            + "where e.login=?");
  }*/

  /*
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder.inMemoryAuthentication()
        .withUser("test")
        .password("test");
  }
  */
}
