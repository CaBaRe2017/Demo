package com.ua.cabare.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ua.cabare.models.Bill;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillDto {

  @JsonProperty("orders")
  private List<OrderPosition> orders = new ArrayList<>();
  @JsonProperty("total_sum")
  private Money totalSum = Money.ZERO;
  @JsonProperty("discounted_sum")
  private Money discountedSum = Money.ZERO;
  @JsonProperty("discount_size")
  private int discountSize;
  @JsonProperty("bill_number")
  private String billNumber;
  @JsonProperty(value = "bill_date")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
  private LocalDateTime billDate;

  public BillDto(Bill bill) {
    bill.getOrderItems().stream().forEach(item -> orders
        .add(new OrderPosition(item.getDishName(), item.getQuantity(), item.getDish().getPrice(),
            item.getComments())));
    this.totalSum = sum();
    if (bill.getDiscount() != null) {
      this.discountSize = bill.getDiscount().getDiscountSize();
      this.discountedSum = totalSum.multiply(discountSize / 100f);
    }
    this.billNumber = bill.getId().toString();
    this.billDate = bill.getBillDate();
  }

  private Money sum() {
    for (OrderPosition order : orders) {
      Money orderItemPrice = order.getPrice().multiply(order.getQuantity());
      totalSum = totalSum.add(orderItemPrice);
    }
    return totalSum;
  }

  public List<OrderPosition> getOrders() {
    return orders;
  }

  public Money getTotalSum() {
    return totalSum;
  }

  public Money getDiscountedSum() {
    return discountedSum;
  }

  public int getDiscountSize() {
    return discountSize;
  }
}

class OrderPosition {

  private String name;
  private int quantity;
  private Money price;
  private String comments;

  public OrderPosition(String name, int quantity, Money price, String comments) {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
    this.comments = comments;
  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  public Money getPrice() {
    return price;
  }

  public String getComments() {
    return comments;
  }
}
