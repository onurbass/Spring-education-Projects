package com.tycase.onurbas.dto.response;

import com.tycase.onurbas.domain.item.Item;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
public class CartResponseDto {
  private List<Item> items;
  private double totalPrice;
  private int appliedPromotionId;
  private double totalDiscount;

  @Override
  public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append("items=").append(items);
	sb.append(", totalPrice=").append(totalPrice);
	sb.append(", appliedPromotionId=").append(appliedPromotionId);
	sb.append(", totalDiscount=").append(totalDiscount);
	sb.append('}');
	return sb.toString();
  }
}

