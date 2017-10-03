package com.ua.cabare.repositiries;

import com.ua.cabare.models.PayType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PayTypeRepository extends JpaRepository<PayType, Long> {

  PayType findPayTypeByTitle(String title);
}
