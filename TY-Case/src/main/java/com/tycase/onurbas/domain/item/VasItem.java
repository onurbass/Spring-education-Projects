package com.tycase.onurbas.domain.item;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class VasItem extends Item {
  @Override
  public String toString() {
	final StringBuilder sb = new StringBuilder("ty.vasItem ->{");
	sb.append("itemId=" + this.getItemId() + ",");
	sb.append(" categoryId:" + this.getCategoryId() + ",");
	sb.append(" sellerId:" + this.getCategoryId() + ",");
	sb.append(" price:" + this.getPrice() + ",");
	sb.append(" quantity:" + this.getQuantity());
	sb.append('}');
	return sb.toString();
  }
}
