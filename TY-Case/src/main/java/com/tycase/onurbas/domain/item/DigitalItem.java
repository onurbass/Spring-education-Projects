package com.tycase.onurbas.domain.item;

import com.tycase.onurbas.domain.enums.ECategory;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class DigitalItem extends Item {

  @Override
  public String toString() {
	final StringBuilder sb = new StringBuilder("ty.item ->{");
	sb.append("itemId=" + this.getItemId() + ",");
	sb.append(" categoryId:" + this.getCategoryId() + ",");
	sb.append(" sellerId:" + this.getCategoryId() + ",");
	sb.append(" price:" + this.getPrice() + ",");
	sb.append(" quantity:" + this.getQuantity());
	sb.append('}');
	return sb.toString();
  }
}
