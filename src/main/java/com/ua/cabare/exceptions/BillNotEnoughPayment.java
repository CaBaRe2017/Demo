package com.ua.cabare.exceptions;

import static com.ua.cabare.domain.ResponseStatus.BILL_NOT_ENOUGH_PAYMENT;

public class BillNotEnoughPayment extends BillException {

  public BillNotEnoughPayment() {
    super(BILL_NOT_ENOUGH_PAYMENT);
  }
}
