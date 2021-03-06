package com.ua.cabare.services;

import static com.ua.cabare.domain.PayStatus.AWAIT;
import static com.ua.cabare.domain.PayStatus.PAID;
import static com.ua.cabare.domain.PayStatus.PREPAID;
import static com.ua.cabare.domain.ResponseStatus.ACCESS_DENIED;
import static com.ua.cabare.domain.ResponseStatus.BILL_NOT_FOUND;
import static com.ua.cabare.domain.ResponseStatus.BILL_NOT_SPECIFIED;
import static com.ua.cabare.domain.ResponseStatus.EMPTY_ORDER_LIST;

import com.ua.cabare.domain.Money;
import com.ua.cabare.domain.PayStatus;
import com.ua.cabare.domain.Utils;
import com.ua.cabare.models.Bill;
import com.ua.cabare.models.Discount;
import com.ua.cabare.models.Dish;
import com.ua.cabare.models.Employee;
import com.ua.cabare.models.OrderItem;
import com.ua.cabare.repositiries.BillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

  @Autowired
  private BillRepository billRepository;
  @Autowired
  private DishService dishService;
  @Autowired
  private DiscountService discountService;
  @Autowired
  private SecurityService securityService;
  @Autowired
  private TimeService timeService;

  public Bill openBill(Bill bill) {
    bill.setEmployee(securityService.getEmployeeFromSession());
    bill.setBillDate(timeService.getCurrentTime());
    bill.setOpened(true);
    List<OrderItem> orderItems = mergeOrders(bill, bill.getOrderItems());
    bill.setOrderItems(orderItems);
    return billRepository.save(bill);
  }

  public Bill updateBill(long billId, List<OrderItem> orderItems) {
    if (orderItems.isEmpty()) {
      throw new RuntimeException(EMPTY_ORDER_LIST);
    }
    Bill billStored = findBill(billId);
    if (billStored.getEmployee().getId() != securityService.getEmployeeFromSession().getId()) {
      throw new RuntimeException(ACCESS_DENIED);
    }
    orderItems = mergeOrders(billStored, orderItems);
    billStored.getOrderItems().addAll(orderItems);
    return billRepository.save(billStored);
  }

  private List<OrderItem> mergeOrders(Bill bill, List<OrderItem> orderItems) {
    resolveDishesInOrders(orderItems);
    for (OrderItem orderItem : orderItems) {
      orderItem.setOrderTime(timeService.getCurrentTime());
    }
    for (OrderItem orderItem : orderItems) {
      orderItem.setTotalPrice(orderItem.getTotalPrice());
      orderItem.setBill(bill);
    }
    return orderItems;
  }

  public Bill updateBill(Bill bill) {
    Bill billStored = findBill(bill.getId());
    Bill billUpdated = Utils.updateState(billStored, bill);
    return billRepository.save(billUpdated);
  }

  public Bill preCloseBill(Long billId, Long discountId) {
    Bill bill = findBill(billId);
    if (discountId != null) {
      Discount discount = resolveDiscount(discountId);
      bill.setDiscount(discount);
    }
    bill.setPayStatus(AWAIT);
    Bill preClosedBill = billRepository.save(bill);
    return preClosedBill;
  }

  public void close(Long billId) {
    Bill bill = findBill(billId);
    bill.setPayStatus(PAID);
    bill.setOpened(false);
    billRepository.save(bill);
  }

  public void payOff(Long billId) {
    Bill bill = findBill(billId);
    if (bill.getPayStatus() != AWAIT) {
      throw new RuntimeException("preclose before");
    }
    bill.setOpened(false);
    bill.setPayStatus(PAID);
    billRepository.save(bill);
  }

  public Bill addPayment(long billId, Money income) {
    Bill bill = findBill(billId);
    bill.setPayStatus(PREPAID);
    bill.addPayment(income);
    return billRepository.save(bill);
  }

  public List<Bill> getOpened() {
    Employee employee = securityService.getEmployeeFromSession();
    return billRepository.findByEmployee(employee);
  }

  public List<Bill> getOpenedAll() {
    return billRepository.findAllByOpened(true);
  }

  public List<Bill> getBillsByPayStatus(PayStatus payStatus) {
    return billRepository.findAllByPayStatus(payStatus);
  }


  private Discount resolveDiscount(Long id) {
    return discountService.getById(id);
  }

  private List<OrderItem> resolveDishesInOrders(List<OrderItem> orderItems) {
    for (OrderItem orderItem : orderItems) {
      Dish dish = orderItem.getDish();
      if (dish != null && dish.getId() != null) {
        Dish dishResolved = dishService.findDish(dish.getId());
        orderItem.setDish(dishResolved);
        orderItem.setDishName(dishResolved.getName());
      }
    }
    return orderItems;
  }

  private Bill findBill(Long id) {
    if (id == null) {
      throw new RuntimeException(BILL_NOT_SPECIFIED);
    }
    return billRepository.findById(id).orElseThrow(() -> new RuntimeException(BILL_NOT_FOUND));
  }
}


