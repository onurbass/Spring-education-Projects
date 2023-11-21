package com.tycase.onurbas.domain.cart;

import com.tycase.onurbas.domain.item.Item;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
  private final int MAX_UNIQUE_ITEM_COUNT = 10;
  private final int MAX_TOTAL_ITEM_COUNT = 30;
  private final double MAX_TOTAL_PRICE = 500000;

  private double totalPrice;
  private double totalDiscount;
  private int appliedPromotionId;


  @Builder.Default
  private List<Item> items = new ArrayList<>();

  public void addItemToCart(Item item) {

	this.items.add((item));
	this.totalPrice = totalPrice + item.getPrice() * item.getQuantity();
  }

  public void removeItemFromCart(Item item) {
	this.items.remove(item);
  }

  public void resetCart() {
	this.items.clear();
	this.totalPrice=0;
	this.appliedPromotionId=0;
	this.totalDiscount=0;

  }
}



