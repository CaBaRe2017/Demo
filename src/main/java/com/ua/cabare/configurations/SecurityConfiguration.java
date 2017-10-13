package com.ua.cabare.configurations;

import com.ua.cabare.repositories.EmployeeRepository;
import com.ua.cabare.security.service.EmployeeDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = {EmployeeRepository.class})
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private EmployeeDetailsService employeeDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(employeeDetailsService).passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
          .antMatchers("/login*", "/logout*", "/employee/registration*", "/registration*").permitAll()
          .antMatchers("/invalidSession*").anonymous()
          //.antMatchers("/employee/updatePassword*","/employee/savePassword*","/updatePassword*").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
          //.anyRequest().hasAuthority("READ_PRIVILEGE")
          .anyRequest().authenticated()
          .and()
        .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/index.html")
          .failureUrl("/login?error=true")
        .permitAll()
        .and()
          .logout()
          .logoutUrl("/logout")
          .clearAuthentication(true)
          .logoutSuccessUrl("/login")
        .permitAll();
  }

  /*private PasswordEncoder getPasswordEncoder() {
    return new PasswordEncoder() {
      @Override
      public String encode(CharSequence charSequence) {
        return charSequence.toString();
      }

      @Override
      public boolean matches(CharSequence charSequence, String s) {
        return true;
      }
    };
  }
*/
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
}
