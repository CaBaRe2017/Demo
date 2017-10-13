package com.ua.cabare.controllers;

import static com.ua.cabare.domain.Response.DEPARTMENT;
import static com.ua.cabare.domain.Response.DEPARTMENTS;
import static com.ua.cabare.domain.Response.STATUS;

import com.ua.cabare.domain.Response;
import com.ua.cabare.models.Department;
import com.ua.cabare.services.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

  @Autowired
  private DepartmentService departmentService;
  @Autowired
  private Response response;

  @RequestMapping(value = "get/all")
  public Response getAll() {
    try {
      List<Department> departments = departmentService.getAll();
      response.put(DEPARTMENTS, departments);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public Response addDepartment(@RequestBody Department department) {
    try {
      Department addedDepartment = departmentService.addDepartment(department);
      response.put(DEPARTMENTS, addedDepartment);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public Response updateDepartment(@RequestBody Department department) {
    try {
      Department updatedDepartment = departmentService.updateDepartment(department);
      response.put(DEPARTMENT, updatedDepartment);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }
}
