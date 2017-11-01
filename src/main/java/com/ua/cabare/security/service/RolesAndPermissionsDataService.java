package com.ua.cabare.security.service;

import com.ua.cabare.models.Role;
import com.ua.cabare.repositories.PrivilegeRepository;
import com.ua.cabare.repositories.RoleRepository;
import com.ua.cabare.security.model.Privilege;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RolesAndPermissionsDataService implements ApplicationListener<ContextRefreshedEvent> {

  private boolean alreadyConfigured = false;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PrivilegeRepository privilegeRepository;

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (alreadyConfigured) {
      return;
    }

    Privilege readPrivilege = createPrivilege("READ");
    Privilege writePrivilege = createPrivilege("WRITE");
    Privilege changePasswordPrivilege = createPrivilege("CHANGE_PASSWORD");

    List<Privilege> adminPrivileges = Arrays
        .asList(readPrivilege, writePrivilege, changePasswordPrivilege);
    List<Privilege> ownerPrivileges = Arrays.asList(readPrivilege);
    List<Privilege> managerPrivileges = Arrays.asList(readPrivilege, changePasswordPrivilege);
    List<Privilege> waiterPrivileges = Arrays.asList(readPrivilege, changePasswordPrivilege);

    createRole("ROLE_ADMIN", adminPrivileges);
    createRole("ROLE_OWNER", ownerPrivileges);
    createRole("ROLE_MANAGER", managerPrivileges);
    createRole("ROLE_WAITER", waiterPrivileges);

    alreadyConfigured = true;
  }

  @Transactional
  private Privilege createPrivilege(String name) {
    Privilege privilege = privilegeRepository.findByName(name);
    if (privilege == null) {
      privilege = new Privilege(name);
      privilegeRepository.save(privilege);
    }
    return privilege;
  }

  @Transactional
  private Role createRole(String name, List<Privilege> privileges) {
    Role role = roleRepository.findByName(name);
    if (role == null) {
      role = new Role(name);
      role.setPrivileges(new HashSet<>(privileges));
      roleRepository.save(role);
    }
    return role;
  }
}
