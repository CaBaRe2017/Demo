package com.ua.cabare.controllers;

import com.ua.cabare.models.Bill;
import com.ua.cabare.services.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bill")
public class BillController {

  @Autowired
  private BillService billService;

  @RequestMapping(value = "/open", method = RequestMethod.PUT)
  public Map<String, Object> openBill(Bill bill) {
    Map<String, Object> response = new HashMap<>();
    Bill openedBill = billService.openBill(bill);
    response.put("bill", openedBill);
    return response;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public Map<String, Object> updateBill(Bill bill) {
    Map<String, Object> response = new HashMap<>();
    Bill updatedBill = billService.updateBill(bill);
    response.put("bill", updatedBill);
    return response;
  }

  @RequestMapping(value = "/close", method = RequestMethod.POST)
  public Map<String, Object> closeBill(Bill bill) {
    Map<String, Object> response = new HashMap<>();
    Bill closedBill = billService.closeBill(bill);
    response.put("bill", closedBill);
    return response;
  }

  @RequestMapping(value = "/all/opened")
  public List<Bill> geAllOpenedBills() {
    return billService.getOpenedBills();
  }
}
