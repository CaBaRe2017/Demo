package com.ua.cabare.repositiries;

import com.ua.cabare.domain.PayStatus;
import com.ua.cabare.models.Bill;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {


  Bill save(Bill bill);

  //  @Query("select b from Bill b where b.payStatus != ?1")
  List<Bill> findAllByPayStatusNot(PayStatus payStatus);

  List<Bill> findAllByOpened(boolean opened);

  List<Bill> findAllByPayStatus(PayStatus payStatus);

  Optional<Bill> findById(long id);

//  List<Bill> findAllByPayStatusNot(PayStatus payStatus);
}
