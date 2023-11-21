package com.tycase.onurbas.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class VasItemRequestDto {
  @NotNull
  @Min(value = 1)
  private int vasItemId;
  @NotNull
  @Min(value = 1)
  private int categoryId;
  @NotNull
  @Min(value = 1)
  private int sellerId;
  @NotNull
  @Min(value = 1)
  private double price;
  @NotNull
  @Min(value = 1)
  private int quantity;
}
