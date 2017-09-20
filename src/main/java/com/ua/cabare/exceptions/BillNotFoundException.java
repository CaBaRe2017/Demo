package com.ua.cabare.exceptions;

import static com.ua.cabare.domain.ResponseStatus.BILL_NOT_FOUND;

public class BillNotFoundException extends BillException {

  public BillNotFoundException() {
    super(BILL_NOT_FOUND);
  }
}
