package com.tycase.onurbas.mapper;

import com.tycase.onurbas.domain.cart.Cart;
import com.tycase.onurbas.domain.item.DefaultItem;
import com.tycase.onurbas.domain.item.DigitalItem;
import com.tycase.onurbas.domain.item.Item;
import com.tycase.onurbas.domain.item.VasItem;
import com.tycase.onurbas.dto.response.CartResponseDto;
import com.tycase.onurbas.dto.request.ItemRequestDto;
import com.tycase.onurbas.dto.request.VasItemRequestDto;

public class Mapper {
  private Mapper() {
  }

  public static Item toItem(ItemRequestDto itemRequestDto) {
	return Item.builder()
			   .itemId(itemRequestDto.getItemId())
			   .categoryId(itemRequestDto.getCategoryId())
			   .sellerId(itemRequestDto.getSellerId())
			   .price(itemRequestDto.getPrice())
			   .quantity(itemRequestDto.getQuantity())
			   .build();
  }

  public static VasItem toVasItem(VasItemRequestDto vasItemRequestDto) {
	return VasItem.builder()
				  .itemId(vasItemRequestDto.getVasItemId())
				  .categoryId(vasItemRequestDto.getCategoryId()).
				  sellerId(vasItemRequestDto.getSellerId())
				  .price(vasItemRequestDto.getPrice())
				  .quantity(vasItemRequestDto.getQuantity())
				  .build();
  }

  public static DefaultItem toDefaultItem(Item item) {
	return DefaultItem.builder()
					  .itemId(item.getItemId())
					  .price(item.getPrice())
					  .quantity(item.getQuantity())
					  .sellerId(item.getSellerId())
					  .categoryId(item.getCategoryId())
					  .build();
  }

  public static DigitalItem toDigitalItem(Item item) {
	return DigitalItem.builder()
					  .itemId(item.getItemId())
					  .price(item.getPrice())
					  .quantity(item.getQuantity())
					  .sellerId(item.getSellerId())
					  .categoryId(item.getCategoryId())
					  .build();
  }

  public static CartResponseDto toCartResponseDto(Cart cart) {
	return CartResponseDto.builder()
						  .items(cart.getItems())
						  .totalPrice(cart.getTotalPrice())
						  .appliedPromotionId(cart.getAppliedPromotionId())
						  .totalDiscount(cart.getTotalDiscount())
						  .build();
  }
}
