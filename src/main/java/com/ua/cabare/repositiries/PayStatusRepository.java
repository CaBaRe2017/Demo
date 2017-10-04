package com.ua.cabare.repositiries;

import com.ua.cabare.models.PayStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PayStatusRepository extends JpaRepository<PayStatus, Long> {

  PayStatus findPayStatusByTitle(String title);
}
