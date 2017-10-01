package com.ua.cabare.services;

import com.ua.cabare.models.Bill;
import com.ua.cabare.repositiries.BillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

  @Autowired
  private BillRepository billRepository;

  public Bill openBill(Bill bill) {

    return billRepository.save(bill);
  }

  public Bill updateBill(Bill bill) {

    return billRepository.save(bill);
  }

  public Bill closeBill(Bill bill) {

    return billRepository.save(bill);
  }

  public List<Bill> getOpenedBills() {
    return billRepository.getOpenedBills();
  }
}

