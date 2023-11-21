package com.tycase.onurbas.service;

import com.tycase.onurbas.domain.cart.Cart;
import com.tycase.onurbas.domain.enums.ECategory;
import com.tycase.onurbas.domain.enums.ESeller;
import com.tycase.onurbas.domain.item.DefaultItem;
import com.tycase.onurbas.domain.item.Item;
import com.tycase.onurbas.domain.item.VasItem;
import com.tycase.onurbas.dto.request.VasItemRequestDto;
import com.tycase.onurbas.dto.response.BaseResponseDto;
import com.tycase.onurbas.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
  private CartService cartService;

  @Lazy
  @Autowired
  public void setCartService(CartService cartService) {
	this.cartService = cartService;
  }

  public ResponseEntity<BaseResponseDto> addVasItemToItem(int itemId,VasItemRequestDto vasItemRequestDto) {
	BaseResponseDto response = new BaseResponseDto();
	try {
	  DefaultItem defaultItem = cartService.findDefaultItemById(itemId,cartService.getCart()).orElse(null);
	  if (defaultItem == null) {
		response.setMessage("Item not found");
		response.setResult(false);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	  }
	  VasItem vasItemToAdd = Mapper.toVasItem(vasItemRequestDto);
	  response = checkVasItemAndDefaultItemIsValid(defaultItem,vasItemToAdd,response);
	  if (!response.isResult()) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	  }

	  response = cartService.applyDiscountToCart(cartService.getCart(),totalPriceWithNewItem(cartService.getCart(),vasItemToAdd),vasItemToAdd,response);
	  if (response.isResult()) {
		cartService.setCartTotalPrice(totalPriceWithNewItem(cartService.getCart(),vasItemToAdd));
		defaultItem.addVasItemToDefaultItem(vasItemToAdd);
		return ResponseEntity.ok(response);
	  } else {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	  }
	} catch (Exception e) {
	  response.setMessage("Unexpected server error" + e.getLocalizedMessage());
	  response.setResult(false);
	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
  }

  public BaseResponseDto checkVasItemAndDefaultItemIsValid(DefaultItem defaultItem,VasItem vasItem,BaseResponseDto response) {
	if (isVasItemExistInItem(defaultItem,vasItem)) {
	  response.setResult(false);
	  response.setMessage("Vas Item already exists in Item");
	  return response;
	}
	if (vasItem.getQuantity() <= 0) {
	  response.setMessage("Invalid vas item quantity");
	  response.setResult(false);
	  return response;
	}
	if (getVasItemQuantityWithNewItem(defaultItem,vasItem) > defaultItem.getMAX_VAS_ITEM_THRESHOLD()) {
	  response.setMessage("A default item cannot have more than " + defaultItem.getMAX_VAS_ITEM_THRESHOLD() + " vas items");
	  response.setResult(false);
	  return response;
	}
	if (!isVasItemCheaperThanDefaultItem(defaultItem,vasItem)) {
	  response.setResult(false);
	  response.setMessage("Vas item cannot be more expensive than the default item.");
	  return response;
	}
	if (!isFurnitureOrElectronicItem(defaultItem)) {
	  response.setMessage("Only furniture or electronic items can have VAS item");
	  response.setResult(false);
	  return response;
	}
	if (!isVasItem(vasItem)) {
	  response.setMessage("Invalid VAS item");
	  response.setResult(false);
	  return response;
	}
	return response;
  }

  public double totalPriceWithNewItem(Cart cart,VasItem vasItem) {
	return cart.getTotalPrice() + vasItem.getPrice() * vasItem.getQuantity();
  }

  public int getVasItemQuantitySum(DefaultItem defaultItem) {
	return defaultItem.getVasItems().stream().mapToInt(VasItem::getQuantity).sum();
  }

  public double getVasItemTotalPriceSum(DefaultItem defaultItem) {
	return defaultItem.getVasItems().stream().mapToDouble(x -> (x.getQuantity() * x.getPrice())).sum();
  }

  public int getVasItemQuantityWithNewItem(DefaultItem defaultItem,VasItem vasItem) {
	return getVasItemQuantitySum(defaultItem) + vasItem.getQuantity();
  }

  public boolean isFurnitureOrElectronicItem(Item item) {
	return item.getCategoryId() == ECategory.FURNITURE.getId() || item.getCategoryId() == ECategory.ELECTRONIC.getId();
  }

  public boolean isVasItem(Item item) {
	return item.getSellerId() == ESeller.VAS_SELLER.getId() || item.getCategoryId() == ECategory.VAS_ITEM.getId();
  }

  public boolean isDigitalItem(Item item) {
	return item.getCategoryId() == ECategory.DIGITAL_ITEM.getId();
  }

  public boolean isVasItemCheaperThanDefaultItem(DefaultItem defaultItem,VasItem vasItem) {
	return defaultItem.getPrice() > vasItem.getPrice();
  }

  public boolean isVasItemExistInItem(DefaultItem defaultItem,VasItem vasItem) {
	return defaultItem.getVasItems().stream()
					  .anyMatch(item -> item.getItemId() == vasItem.getItemId());
  }
}


