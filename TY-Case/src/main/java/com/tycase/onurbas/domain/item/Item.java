package com.tycase.onurbas.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Item {
  @ToString.Exclude
  private final int MAX_ITEM_QUANTITY = 10;
  private int itemId;
  private int categoryId;
  private int sellerId;
  private double price;
  private int quantity;

  @Override
  public String toString() {
	final StringBuilder sb = new StringBuilder("ty.item ->{");
	sb.append("itemId=" + this.getItemId() + ",");
	sb.append("categoryId:" + this.getCategoryId() + ",");
	sb.append("sellerId:" + this.getCategoryId() + ",");
	sb.append("price:" + this.getPrice() + ",");
	sb.append("quantity:" + this.getQuantity());
	sb.append('}');
	return sb.toString();
  }
}
