package com.ua.cabare.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;

@Component
@RequestScope
public class Response extends HashMap<String, Object> {

  public static final String BILL = "bill";
  public static final String BILL_LIST = "bill_list";
  public static final String CASHBACK = "cashback";
  public static final String STATUS = "status";

  public Response() {
    put(STATUS, "ok");
  }
}
