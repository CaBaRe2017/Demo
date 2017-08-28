package com.ua.cabare.controllers;

import com.ua.cabare.models.Bill;
import com.ua.cabare.repositiries.BillRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {

  @Autowired
  private BillRepositories billRepositories;

  @RequestMapping(value = "/open", method = RequestMethod.PUT)
  public Bill openBill(Bill bill) {
    return billRepositories.save(bill);
  }

}
