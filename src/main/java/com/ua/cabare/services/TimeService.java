package com.ua.cabare.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TimeService {

  public LocalDateTime getCurrentTime() {
    return LocalDateTime.now(ZoneId.of("UTC+3"));
  }
}
