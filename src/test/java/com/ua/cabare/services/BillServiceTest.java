package com.ua.cabare.services;

import static com.ua.cabare.domain.PayStatus.AWAIT;
import static com.ua.cabare.domain.PayStatus.PAID;
import static com.ua.cabare.domain.PayStatus.PREPAID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ua.cabare.domain.BillCashbackTuple;
import com.ua.cabare.domain.Money;
import com.ua.cabare.exceptions.BillNotEnoughPayment;
import com.ua.cabare.models.Bill;
import com.ua.cabare.models.OrderItem;
import com.ua.cabare.repositiries.BillRepository;
import com.ua.cabare.services.BillService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

  Bill bill;
  @Mock
  BillRepository billRepository;
  @InjectMocks
  BillService billService;


  @Before
  public void setUp() throws Exception {
    bill = new Bill();
  }

  @Test
  public void openBillShouldReturnPayStatusAWAIT() throws Exception {
    Money totalCost = new Money(90);
    Money payment = Money.ZERO;

    bill = spy(bill);
    when(bill.getOrdersCost()).thenReturn(totalCost);
    when(billRepository.save(any(Bill.class))).thenReturn(bill);

    Bill savedBill = billService.openBill(this.bill, payment);

    assertThat(savedBill.getPayStatus()).isEqualTo(AWAIT);
    assertThat(savedBill.getPaid().equals(payment)).isTrue();
  }

  @Test
  public void openBillShouldReturnPayStatusPREPAID() {
    Money totalCost = new Money(90);
    Money payment = new Money(100);

    bill = spy(bill);
    when(bill.getOrdersCost()).thenReturn(totalCost);
    when(billRepository.save(any(Bill.class))).thenReturn(bill);

    Bill savedBill = billService.openBill(this.bill, payment);

    assertThat(savedBill.getPayStatus()).isEqualTo(PREPAID);
    assertThat(savedBill.getPaid().equals(payment)).isTrue();
  }

  @Test(expected = BillNotEnoughPayment.class)
  public void closeBillSouldReturnThrowBillNotEnoughPaymentAndPayStatusPAID() throws Exception {
    Money totalCost = new Money(90);
    Money paidBefore = new Money(50);

    bill = spy(bill);
    when(bill.getOrdersCost()).thenReturn(totalCost);
    when(bill.getPaid()).thenReturn(paidBefore);
    when(billRepository.findById(any(Long.class))).thenReturn(Optional.of(bill));
    when(billRepository.save(any(Bill.class))).thenReturn(bill);

    BillCashbackTuple billCashbackTuple = billService.closeBill(1);
    assertThat(billCashbackTuple.bill.getPayStatus()).isEqualTo(PREPAID);
  }

  @Test
  public void closeBillSouldReturn60CashbackAndPayStatusPAID() throws Exception {
    Money totalCost = new Money(90);
    Money paidBefore = new Money(150);
    Money cashback = new Money(60);

    bill = spy(bill);
    when(bill.getOrdersCost()).thenReturn(totalCost);
    when(bill.getPaid()).thenReturn(paidBefore);
    when(billRepository.findById(anyLong())).thenReturn(Optional.of(bill));
    when(billRepository.save(any(Bill.class))).thenReturn(bill);

    BillCashbackTuple billCashbackTuple = billService.closeBill(1);
    assertThat(billCashbackTuple.cashback.equals(cashback)).isTrue();
    assertThat(billCashbackTuple.bill.getPayStatus()).isEqualTo(PAID);
  }

  @Test
  public void updateBillShouldAddOrdersList() throws Exception {
    when(billRepository.findById(anyLong())).thenReturn(Optional.of(bill));

    billService.updateBill(1, Arrays.asList(new OrderItem()));
    assertThat(bill.getOrderItems().size()).isEqualTo(1);
    verify(billRepository, times(1)).save(any(Bill.class));
  }
}