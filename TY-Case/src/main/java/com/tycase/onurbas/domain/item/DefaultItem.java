package com.tycase.onurbas.domain.item;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DefaultItem extends Item {

  private final int MAX_VAS_ITEM_THRESHOLD = 3;

  @Builder.Default
  private List<VasItem> vasItems = new ArrayList<>();

  public void addVasItemToDefaultItem(VasItem vasItem) {

	this.vasItems.add(vasItem);
  }

  @Override
  public String toString() {
	final StringBuilder sb = new StringBuilder("ty.item ->{");
	sb.append("itemId=" + this.getItemId() + ",");
	sb.append(" categoryId:" + this.getCategoryId() + ",");
	sb.append(" sellerId:" + this.getCategoryId() + ",");
	sb.append(" price:" + this.getPrice() + ",");
	sb.append(" quantity:" + this.getQuantity());
	sb.append(" vasItems=").append(vasItems);
	sb.append('}');
	return sb.toString();
  }
}
