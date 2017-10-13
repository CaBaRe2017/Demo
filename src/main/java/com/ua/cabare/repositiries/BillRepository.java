package com.ua.cabare.repositiries;

import com.ua.cabare.domain.PayStatus;
import com.ua.cabare.models.Bill;
import com.ua.cabare.models.Employee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {


  Bill save(Bill bill);

  List<Bill> findAllByPayStatusNot(PayStatus payStatus);

  List<Bill> findAllByOpened(boolean opened);

  @Query("select b from Bill b where b.opened = true and b.employee = ?1")
  List<Bill> findByEmployee(Employee employee);

  List<Bill> findAllByPayStatus(PayStatus payStatus);

  Optional<Bill> findById(long id);
}
