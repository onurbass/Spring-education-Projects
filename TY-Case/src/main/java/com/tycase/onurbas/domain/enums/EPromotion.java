package com.tycase.onurbas.domain.enums;

import lombok.Getter;

@Getter
public enum EPromotion {
  SAME_SELLER_PROMOTION(9909,0.10),
  CATEGORY_PROMOTION(5676,0.05),
  TOTAL_PRICE_PROMOTION(1232),
  ;
  private final int id;
  private double discountRate;

  EPromotion(int id) {
	this.id = id;
  }

  EPromotion(int id,double discountRate) {
	this.id = id;
	this.discountRate = discountRate;
  }
}
