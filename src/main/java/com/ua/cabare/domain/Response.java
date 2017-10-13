package com.ua.cabare.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;

@Component
@RequestScope
public class Response extends HashMap<String, Object> {

  public static final String BILL = "bill";
  public static final String BILL_LIST = "bill_list";
  public static final String STATUS = "status";
  public static final String DISCOUNT_CARD = "discount_card";
  public static final String DISCOUNT_SIZE = "discount_size";
  public static final String DEPARTMENTS = "departments";
  public static final String DEPARTMENT = "department";
  public static final String BILL_PRICE = "bill_price";

  public Response() {
    put(STATUS, "ok");
  }
}
