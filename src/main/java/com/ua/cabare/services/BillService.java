package com.ua.cabare.services;

import static com.ua.cabare.domain.PayStatus.AWAIT;
import static com.ua.cabare.domain.PayStatus.PAID;
import static com.ua.cabare.domain.PayStatus.PREPAID;

import com.ua.cabare.domain.BillCashbackTuple;
import com.ua.cabare.domain.Money;
import com.ua.cabare.domain.PayStatus;
import com.ua.cabare.exceptions.BillNotEnoughPayment;
import com.ua.cabare.exceptions.BillNotFoundException;
import com.ua.cabare.models.Bill;
import com.ua.cabare.models.OrderItem;
import com.ua.cabare.repositiries.BillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

  @Autowired
  private BillRepository billRepository;

  public void setBillRepository(BillRepository billRepository) {
    this.billRepository = billRepository;
  }

  public Bill openBill(Bill bill, Money income) {
    bill = changePayStatus(bill, income);
    return billRepository.save(bill);
  }

  public void updateBill(long billId, List<OrderItem> orderItems) throws BillNotFoundException {
    Bill bill = findBill(billId);
    bill.getOrderItems().addAll(orderItems);
    billRepository.save(bill);
  }

  public void updateBill(long billId, int table) throws BillNotFoundException {
    Bill bill = findBill(billId);
    bill.setTableCount(table);
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


