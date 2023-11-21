package com.tycase.onurbas.domain.enums;

import lombok.Getter;

@Getter
public enum ESeller {

  VAS_SELLER(5003);
  private final int id;

  ESeller(int id) {
	this.id = id;
  }
}
