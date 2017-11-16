package com.ua.cabare.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ua.cabare.domain.Money;
import com.ua.cabare.models.Bill;
import com.ua.cabare.models.Dish;
import com.ua.cabare.models.Employee;
import com.ua.cabare.models.OrderItem;
import com.ua.cabare.repositiries.BillRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BillServiceTest {

  Bill bill;
  Employee employee;
  @Autowired
  BillService billService;
  @MockBean
  BillRepository billRepository;
  @MockBean
  DishService dishService;
  @MockBean
  private DiscountService discountService;
  @MockBean
  private SecurityService securityService;


  @Before
  public void setUp() throws Exception {
    bill = new Bill();
    employee = new Employee();
    employee.setId(1L);
    bill.setEmployee(employee);
  }

  @Test
  public void openBillShouldSetOpenedTrue() throws Exception {

    when(securityService.getEmployeeFromSession()).thenReturn(employee);
    when(billRepository.save(any(Bill.class))).thenReturn(bill);

    Bill savedBill = billService.openBill(this.bill);

    assertTrue(savedBill.isOpened());
    assertThat(savedBill.getBillDate()).isNotNull();
    assertThat(savedBill.getEmployee()).isEqualTo(employee);
    verify(billRepository, times(1)).save(any(Bill.class));
  }

  @Test(expected = RuntimeException.class)
  public void updateBillShouldThrowRuntimeException() throws Exception {
    billService.updateBill(1, Collections.EMPTY_LIST);
  }

  @Test
  public void updateBillShouldAddOrdersListAndSetCurrentTime() throws Exception {
    OrderItem orderItem = new OrderItem();
    Dish dish = new Dish();
    dish.setId(5L);
    dish.setPrice(Money.valueOf(100));
    orderItem.setQuantity(2);
    orderItem.setDish(dish);

    when(billRepository.findById(anyLong())).thenReturn(Optional.of(bill));
    when(billRepository.save(any(Bill.class))).thenReturn(bill);
    when(dishService.findDish(any())).thenReturn(dish);
    when(securityService.getEmployeeFromSession()).thenReturn(employee);

    Bill updatedBill = billService.updateBill(1, Arrays.asList(orderItem));

    assertThat(updatedBill.getOrderItems().size()).isEqualTo(1);
    assertThat(updatedBill.getOrderItems().get(0).getOrderTime())
        .isLessThan(LocalDateTime.now());
    assertThat(updatedBill.getOrderItems().get(0).getOrderTime())
        .isGreaterThan(LocalDateTime.now().minusSeconds(2));
    verify(billRepository, times(1)).save(any(Bill.class));
  }
}