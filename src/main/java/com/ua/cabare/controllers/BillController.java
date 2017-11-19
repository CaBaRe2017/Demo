package com.ua.cabare.controllers;

import static com.ua.cabare.domain.Response.BILL;
import static com.ua.cabare.domain.Response.BILL_LIST;
import static com.ua.cabare.domain.Response.STATUS;

import com.ua.cabare.domain.BillDto;
import com.ua.cabare.domain.Money;
import com.ua.cabare.domain.PayStatus;
import com.ua.cabare.domain.Response;
import com.ua.cabare.models.Bill;
import com.ua.cabare.models.OrderItem;
import com.ua.cabare.services.BillService;
import com.ua.cabare.services.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bill")
public class BillController {

  @Autowired
  private BillService billService;
  @Autowired
  private Response response;
  @Autowired
  private SecurityService securityService;

  public void setBillService(BillService billService) {
    this.billService = billService;
  }

  public void setResponse(Response response) {
    this.response = response;
  }

  @RequestMapping(value = "/open", method = RequestMethod.PUT)
  public Response openBill(@RequestBody Bill bill, @RequestParam Long employeeId) {
    try {
      securityService.authorizeEmployee(employeeId);
      bill = billService.openBill(bill);
      response.put(BILL, new BillDto(bill));
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
      ex.printStackTrace();
    }
    return response;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public Response updateBill(@RequestBody Bill bill) {
    try {
      Bill updatedBill = billService.updateBill(bill);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/add/orderitems", method = RequestMethod.PUT)
  public Response addOrder(@RequestParam long billId, @RequestBody List<OrderItem> orderItems,
      @RequestParam Long employeeId) {
    try {
      securityService.authorizeEmployee(employeeId);
      Bill bill = billService.updateBill(billId, orderItems);
      response.put(BILL, new BillDto(bill));
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/add/payment", method = RequestMethod.PUT)
  public Response addPayment(@RequestParam long billId, @RequestParam Money income) {
    try {
      Bill bill = billService.addPayment(billId, income);
      response.put(BILL, bill);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/preclose", method = RequestMethod.POST)
  public Response preCloseBill(@RequestParam(name = "bill_id") Long billId,
      @RequestParam(name = "discount_id") Long discountId) {
    try {
      Bill bill = billService.preCloseBill(billId, discountId);
      response.put(BILL, new BillDto(bill));
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/close", method = RequestMethod.POST)
  public Response close(@RequestParam(name = "bill_id") Long billId) {
    try {
      billService.close(billId);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/payoff", method = RequestMethod.POST)
  public Response payOff(@RequestParam("bill_id") Long billId) {
    try {
      billService.payOff(billId);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/opened", method = RequestMethod.POST)
  public Response getOpened(@RequestParam Long employeeId) {
    try {
      securityService.authorizeEmployee(employeeId);
      List<BillDto> openedBills = billService.getOpened()
          .stream()
          .map(item -> new BillDto(item))
          .collect(Collectors.toList());
      response.put(BILL_LIST, openedBills);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/all/opened")
  public Response getOpenedAll() {
    try {

      List<BillDto> openedBills = billService.getOpenedAll()
          .stream()
          .map(item -> new BillDto(item))
          .collect(Collectors.toList());
      response.put(BILL_LIST, openedBills);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/all/{paystatus}")
  public Response getBillsByPayStatus(@PathVariable(name = "paystatus") PayStatus payStatus) {
    try {
      List<Bill> bills = billService.getBillsByPayStatus(payStatus);
      response.put(BILL_LIST, bills);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }
}
