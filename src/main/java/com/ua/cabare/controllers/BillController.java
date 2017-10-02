package com.ua.cabare.controllers;

import static com.ua.cabare.domain.Response.BILL;
import static com.ua.cabare.domain.Response.BILL_LIST;
import static com.ua.cabare.domain.Response.CASHBACK;
import static com.ua.cabare.domain.Response.STATUS;

import com.ua.cabare.domain.BillCashbackTuple;
import com.ua.cabare.domain.Money;
import com.ua.cabare.domain.Response;
import com.ua.cabare.exceptions.BillException;
import com.ua.cabare.exceptions.DishException;
import com.ua.cabare.exceptions.DishNotFoundException;
import com.ua.cabare.models.Bill;
import com.ua.cabare.models.OrderItem;
import com.ua.cabare.models.PayStatus;
import com.ua.cabare.services.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

  @Autowired
  private BillService billService;
  @Autowired
  private Response response;

  public void setBillService(BillService billService) {
    this.billService = billService;
  }

  public void setResponse(Response response) {
    this.response = response;
  }

  @RequestMapping(value = "/open", method = RequestMethod.PUT)
  public Response openBill(@RequestBody Bill bill, @RequestParam Money income) {
    try {
      bill = billService.openBill(bill, income);
      response.put(BILL, bill);
    } catch (DishNotFoundException ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/add/orderitems", method = RequestMethod.PUT)
  public Response addOrder(@RequestParam long billId, @RequestParam List<OrderItem> orderItems) {
    try {
      billService.updateBill(billId, orderItems);
    } catch (BillException | DishException ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/add/payment", method = RequestMethod.PUT)
  public Response addPayment(@RequestParam long billId, @RequestParam Money income) {
    try {
      billService.addPayment(billId, income);
    } catch (BillException ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/change/table", method = RequestMethod.PUT)
  public Response changeTable(@RequestParam long billId, @RequestParam int table) {
    try {
      billService.updateBill(billId, table);
    } catch (BillException ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/close/{id}")
  public Response closeBill(long id) {
    BillCashbackTuple billCashbackTuple = null;
    try {
      billCashbackTuple = billService.closeBill(id);
      response.put(BILL, billCashbackTuple.bill);
      response.put(CASHBACK, billCashbackTuple.cashback);
    } catch (BillException ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/all/opened")
  public Response geAllOpenedBills() {
    response.put(BILL_LIST, billService.getOpenedBills());
    return response;
  }

  @RequestMapping(value = "/all/{paystatus}")
  public Response getBillsByPayStatus(@PathVariable(name = "paystatus") PayStatus payStatus) {
    List<Bill> bills = billService.getBillsByPayStatus(payStatus);
    response.put(BILL_LIST, bills);
    return response;
  }
}
