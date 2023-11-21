package com.tycase.onurbas.domain.enums;

import lombok.Getter;

@Getter
public enum ECategory {

  FURNITURE(1001),ELECTRONIC(3004),VAS_ITEM(3242),DIGITAL_ITEM(7889),ITEM_THAT_HAS_CATEGORY_PROMOTION(3003);

  private final int id;

  ECategory(int id) {
	this.id = id;
  }
}