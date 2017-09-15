package com.ua.cabare.repositiries;

import com.ua.cabare.models.Bill;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class BillRepository {

  private static Set<Bill> bills;
  private static long counter = 0;

  static {
    bills = new HashSet<>();
    Bill billFirst = new Bill();
    billFirst.setId(++counter);
    bills.add(billFirst);
    Bill billSecond = new Bill();
    billSecond.setId(++counter);
    bills.add(billSecond);
  }

  public Bill save(Bill bill) {
    if (bill.getId() == 0) {
      bill.setId(++counter);
    }
    bills.add(bill);
    return bill;
  }

  public List<Bill> getOpenedBills() {
    return null;
  }
}
