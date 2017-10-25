package com.ua.cabare.services;

import static com.ua.cabare.domain.PayStatus.AWAIT;
import static com.ua.cabare.domain.PayStatus.PAID;
import static com.ua.cabare.domain.PayStatus.PREPAID;
import static com.ua.cabare.domain.ResponseStatus.BILL_NOT_FOUND;
import static com.ua.cabare.domain.ResponseStatus.BILL_NOT_SPECIFIED;
import static com.ua.cabare.domain.ResponseStatus.DISCOUNT_CARD_NOT_FOUND;
import static com.ua.cabare.domain.ResponseStatus.EMPTY_ORDER_LIST;

import com.ua.cabare.domain.Money;
import com.ua.cabare.domain.PayStatus;
import com.ua.cabare.models.Bill;
import com.ua.cabare.models.Discount;
import com.ua.cabare.models.Dish;
import com.ua.cabare.models.Employee;
import com.ua.cabare.models.OrderItem;
import com.ua.cabare.repositiries.BillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    return billRepository.save(bill);
  }

  public Bill updateBill(long billId, List<OrderItem> orderItems) {
    if (orderItems.isEmpty()) {
      throw new RuntimeException(EMPTY_ORDER_LIST);
    }
    Bill billStored = findBill(billId);
    orderItems = resolveDishesInOrders(orderItems);
    for (OrderItem orderItem : orderItems) {
      orderItem.setOrderTime(timeService.getCurrentTime());
    }
    for (OrderItem orderItem : orderItems) {
      orderItem.setTotalPrice(orderItem.getTotalPrice());
    }
    billStored.getOrderItems().addAll(orderItems);
    return billRepository.save(billStored);
  }

  public Bill updateBill(Bill bill) {
    Long id = bill.getId();
    if (id == null) {
      throw new RuntimeException(BILL_NOT_SPECIFIED);
    }
    Bill billStored = findBill(id);
    if (bill.getTableNumber() != null) {
      billStored.setTableNumber(bill.getTableNumber());
    }
    if (bill.getNumberOfPersons() != null) {
      billStored.setNumberOfPersons(bill.getNumberOfPersons());
    }
    if (bill.getSaleType() != null) {
      billStored.setPayType(bill.getPayType());
    }
    if (bill.getDiscount() != null) {
      billStored.setDiscount(bill.getDiscount());
    }
    return billRepository.save(billStored);
  }

  public Bill preCloseBill(Long billId, Discount discount) {
    Bill bill = findBill(billId);
    discount = resolveDiscount(discount);
    bill.setDiscount(discount);
    bill.setPayStatus(AWAIT);
    Bill preClosedBill = billRepository.save(bill);
    return preClosedBill;
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


  private Discount resolveDiscount(Discount discount) {
    if (discount.getId() != null) {
      return discountService.getById(discount.getId());
    }
    if (discount.getDiscountCard() != null) {
      return discountService.getDiscountCard(discount.getDiscountCard());
    }
    throw new RuntimeException(DISCOUNT_CARD_NOT_FOUND);
  }

  private List<OrderItem> resolveDishesInOrders(List<OrderItem> orderItems) {
    List<OrderItem> resolvedOrders = new ArrayList<>();
    for (OrderItem orderItem : orderItems) {
      Dish dish = orderItem.getDish();
      if (dish != null && dish.getId() != null) {
        Dish dishResolved = dishService.findDish(dish.getId());
        orderItem.setDish(dishResolved);
        resolvedOrders.add(orderItem);
      }
    }
    return resolvedOrders;
  }

  private Bill findBill(long id) {
    return billRepository.findById(id).orElseThrow(() -> new RuntimeException(BILL_NOT_FOUND));
  }
}


