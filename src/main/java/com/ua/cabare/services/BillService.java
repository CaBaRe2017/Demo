package com.ua.cabare.services;

import static com.ua.cabare.domain.PayStatus.AWAIT;
import static com.ua.cabare.domain.PayStatus.PAID;
import static com.ua.cabare.domain.PayStatus.PREPAID;

import com.ua.cabare.domain.BillCashbackTuple;
import com.ua.cabare.domain.Money;
import com.ua.cabare.domain.PayStatus;
import com.ua.cabare.exceptions.BillNotEnoughPayment;
import com.ua.cabare.exceptions.BillNotFoundException;
import com.ua.cabare.exceptions.DishNotFoundException;
import com.ua.cabare.models.Bill;
import com.ua.cabare.models.Dish;
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

  public void setBillRepository(BillRepository billRepository) {
    this.billRepository = billRepository;
  }

  public void setDishService(DishService dishService) {
    this.dishService = dishService;
  }

  public Bill openBill(Bill bill, Money income) throws DishNotFoundException {
    bill = changePayStatus(bill, income);
    List<OrderItem> orderItems = resolveDishesInOrders(bill.getOrderItems());
    bill.setOrderItems(orderItems);
    return billRepository.save(bill);
  }

  public void updateBill(long billId, List<OrderItem> orderItems)
      throws BillNotFoundException, DishNotFoundException {
    Bill bill = findBill(billId);
    orderItems = resolveDishesInOrders(orderItems);
    if (orderItems.isEmpty()) {
      return;
    }
    bill.getOrderItems().addAll(orderItems);
    billRepository.save(bill);
  }

  private List<OrderItem> resolveDishesInOrders(List<OrderItem> orderItems)
      throws DishNotFoundException {
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

  public void updateBill(long billId, int table) throws BillNotFoundException {
    Bill bill = findBill(billId);
    bill.setTableNumber(table);
    billRepository.save(bill);
  }

  private Bill findBill(long id) throws BillNotFoundException {
    return billRepository.findById(id).orElseThrow(() -> new BillNotFoundException());
  }

  public BillCashbackTuple closeBill(long billId)
      throws BillNotFoundException, BillNotEnoughPayment {
    Bill bill = findBill(billId);
    Money cashback = countCashback(bill);
    bill.setPayStatus(PAID);
    Bill closedBill = billRepository.save(bill);
    return new BillCashbackTuple(closedBill, cashback);
  }

  public void addPayment(long billId, Money income) throws BillNotFoundException {
    Bill bill = findBill(billId);
    bill.addPayment(income);
    billRepository.save(bill);
  }

  private Money countCashback(Bill bill) throws BillNotEnoughPayment {
    Money ordersCost = bill.getOrdersCost();
    Money billMoneyPaid = bill.getPaid();
    if (ordersCost.isMoreThan(billMoneyPaid)) {
      throw new BillNotEnoughPayment();
    }
    return billMoneyPaid.subtract(ordersCost);
  }

  private Bill changePayStatus(Bill bill, Money income) {
    Money totalOrderCost = bill.getOrdersCost();
    Money totalPayment = bill.getPaid().add(income);

    if (totalPayment == Money.ZERO || totalOrderCost.isMoreThan(totalPayment)) {
      bill.setPayStatus(AWAIT);
    } else {
      bill.setPayStatus(PREPAID);
    }
    bill.setPaid(totalPayment);
    return bill;
  }

  ///////////////////////
  public List<Bill> getOpenedBills() {
    return billRepository.findAllByOpened(true);
  }


  public List<Bill> getBillsByPayStatus(PayStatus payStatus) {
    return billRepository.findAllByPayStatus(payStatus);
  }
}


