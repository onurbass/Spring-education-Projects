package com.tycase.onurbas.service;

import com.tycase.onurbas.domain.cart.Cart;
import com.tycase.onurbas.domain.enums.ECategory;
import com.tycase.onurbas.domain.enums.EPromotion;
import com.tycase.onurbas.domain.item.DefaultItem;
import com.tycase.onurbas.domain.item.DigitalItem;
import com.tycase.onurbas.domain.item.Item;
import com.tycase.onurbas.domain.item.VasItem;
import com.tycase.onurbas.dto.request.ItemRequestDto;
import com.tycase.onurbas.dto.response.BaseResponseDto;
import com.tycase.onurbas.mapper.Mapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
public class CartService {
  private final Cart cart = new Cart();
  private ItemService itemService;

  @Autowired
  @Lazy
  public void setItemService(ItemService itemService) {
	this.itemService = itemService;
  }

  public ResponseEntity<BaseResponseDto> addItemToCart(ItemRequestDto itemRequestDto) {

	Item item = Mapper.toItem(itemRequestDto);
	BaseResponseDto response = checkItemAndCartIsValid(cart,item);
	try {
	  if (!response.isResult()) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	  }
	  if (itemService.isDigitalItem(item)) {
		return addDigitalItemToCart(cart,item,response);
	  }
	  if (isCartHasDigitalItem(cart)) {
		response.setMessage("Other items cannot add to cart which has digital item");
		response.setResult(false);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	  }
	  if (itemService.isFurnitureOrElectronicItem(item)) {
		return addDefaultItemToCart(cart,item,response);
	  }
	  double totalPriceWithNewItem = cart.getTotalPrice() + item.getPrice() * item.getQuantity();
	  response = applyDiscountToCart(cart,totalPriceWithNewItem,item,response);
	  if (response.isResult()) {
		cart.addItemToCart(item);
	  } else {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	  }
	} catch (Exception e) {
	  response.setMessage("Unexpected server error" + e.getLocalizedMessage());
	  response.setResult(false);
	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	return ResponseEntity.ok(response);
  }

  public BaseResponseDto checkItemAndCartIsValid(Cart cart,Item item) {
	BaseResponseDto response = new BaseResponseDto();
	if (isMaxUniqueItemReached(cart)) {
	  response.setMessage("Unique item limit exceeded.");
	  response.setResult(false);
	  return response;
	}
	if (!isItemQuantityFitForCart(item)) {
	  response.setResult(false);
	  response.setMessage("Only " + item.getMAX_ITEM_QUANTITY() + " items can be added to the cart.");
	  return response;
	}
	if (isItemExistInCart(cart,item)) {
	  response.setMessage("Item already exists in cart");
	  response.setResult(false);
	  return response;
	}
	if (item.getQuantity() == 0) {
	  response.setMessage("Item quantity must be more than zero");
	  response.setResult(false);
	  return response;
	}
	if (cartQuantityWithNewItem(cart,item) > cart.getMAX_TOTAL_ITEM_COUNT()) {
	  response.setMessage("Cart cannot have more than " + cart.getMAX_TOTAL_ITEM_COUNT() + " items");
	  response.setResult(false);
	  return response;
	}
	if (itemService.isVasItem(item)) {
	  response.setMessage("Vas Items cannot add to cart directly");
	  response.setResult(false);
	  return response;
	}
	return response;
  }

  public boolean isMaxUniqueItemReached(Cart cart) {
	return cart.getItems().size() + 1 > 10;
  }

  public int cartQuantityWithNewItem(Cart cart,Item item) {
	return cart.getItems().stream().mapToInt(Item::getQuantity).sum() + item.getQuantity();
  }

  public boolean isItemQuantityFitForCart(Item item) {
	return item.getQuantity() <= item.getMAX_ITEM_QUANTITY();
  }

  public boolean isItemExistInCart(Cart cart,Item item) {
	return cart.getItems().stream().anyMatch(i -> i.getItemId() == item.getItemId());
  }

  public Optional<Item> findItemById(int itemId) {
	return cart.getItems().stream().filter(i -> i.getItemId() == itemId).findFirst();
  }

  public ResponseEntity<BaseResponseDto> addDigitalItemToCart(Cart cart,Item item,BaseResponseDto response) {
	response = verifyCartForDigitalItem(cart.getItems(),response);
	if (!response.isResult()) {
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	response = applyDiscountToCart(cart,cart.getTotalPrice() + item.getPrice() * item.getQuantity(),item,response);
	if (!response.isResult()) {
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	DigitalItem digitalItem = Mapper.toDigitalItem(item);
	cart.addItemToCart(digitalItem);
	return ResponseEntity.ok(response);
  }

  public ResponseEntity<BaseResponseDto> addDefaultItemToCart(Cart cart,Item item,BaseResponseDto response) {
	DefaultItem defaultItem = Mapper.toDefaultItem(item);

	response = applyDiscountToCart(cart,cart.getTotalPrice() + defaultItem.getPrice() * defaultItem.getQuantity(),defaultItem,response);
	if (response.isResult()) {
	  cart.addItemToCart(defaultItem);
	  return ResponseEntity.ok(response);
	} else {
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
  }

  public BaseResponseDto verifyCartForDigitalItem(List<Item> cartItems,BaseResponseDto response) {
	Optional<Item> optionalItem = cartItems.stream().filter(i -> i.getCategoryId() != ECategory.DIGITAL_ITEM.getId()).findAny();
	if (optionalItem.isPresent()) {
	  response.setMessage("There are items in the cart other than digital items ");
	  response.setResult(false);
	  return response;
	}
	long digitalItemCount = cartItems.stream().filter(i -> i.getCategoryId() == ECategory.DIGITAL_ITEM.getId()).count();
	if (digitalItemCount >= 5) {
	  response.setMessage("More than 5 digital items cannot be added to the cart");
	  response.setResult(false);
	  return response;
	}
	return response;
  }

  public Optional<DefaultItem> findDefaultItemById(int itemId,Cart cart) {
	Item item = cart.getItems().stream().filter(i -> i.getItemId() == itemId).findFirst().orElse(null);
	if (item != null && (itemService.isFurnitureOrElectronicItem(item))) {
	  return Optional.of((DefaultItem) item);
	}
	return Optional.empty();
  }

  public BaseResponseDto applyDiscountToCart(Cart cart,double totalPrice,Item item,BaseResponseDto response) {
	double maxDiscount = 0, sameSellerDiscount = 0, categoryDiscount = 0;
	int promotionId = 0;
	double totalPricePromotionDiscount = calculateTotalPricePromotionDiscount(totalPrice);
	if (isCartFitForSameSellerPromotion(item,cart)) {
	  sameSellerDiscount = totalPrice * EPromotion.SAME_SELLER_PROMOTION.getDiscountRate();
	}
	if (isCartFitForCategoryPromotion(item,cart)) {
	  categoryDiscount = totalPrice * EPromotion.CATEGORY_PROMOTION.getDiscountRate();
	}

	if (sameSellerDiscount > maxDiscount) {
	  maxDiscount = sameSellerDiscount;
	  promotionId = EPromotion.SAME_SELLER_PROMOTION.getId();
	}
	if (categoryDiscount > maxDiscount) {
	  maxDiscount = categoryDiscount;
	  promotionId = EPromotion.CATEGORY_PROMOTION.getId();
	}
	if (totalPricePromotionDiscount > maxDiscount) {
	  maxDiscount = totalPricePromotionDiscount;
	  promotionId = EPromotion.TOTAL_PRICE_PROMOTION.getId();
	}
	if (totalPrice - maxDiscount > this.cart.getMAX_TOTAL_PRICE()) {
	  response.setMessage("Cart total price cannot be more than " + this.cart.getMAX_TOTAL_PRICE());
	  response.setResult(false);
	  return response;
	}
	cart.setTotalDiscount(maxDiscount);
	cart.setAppliedPromotionId(promotionId);
	return response;
  }

  public boolean isCartFitForSameSellerPromotion(Item item,Cart cart) {
	List<Item> items = new ArrayList<>(cart.getItems());
	if (items.isEmpty() || item instanceof VasItem) {
	  return cart.getAppliedPromotionId() == EPromotion.SAME_SELLER_PROMOTION.getId();
	}
	if (isItemExistInCart(cart,item)) {
	  items.remove(item);
	  if (items.isEmpty() || items.size() == 1) {
		return false;
	  }
	  long firstSellerIdWhileRemoving = items.get(0).getSellerId();
	  return items.stream().allMatch(it -> it.getSellerId() == firstSellerIdWhileRemoving);
	} else {
	  long firstSellerIdWhileAdding = items.get(0).getSellerId();
	  return firstSellerIdWhileAdding == item.getSellerId();
	}
  }

  public boolean isCartFitForCategoryPromotion(Item item,Cart cart) {
	List<Item> items = new ArrayList<>(cart.getItems());
	if (isItemExistInCart(cart,item)) {
	  items.removeIf(i -> i.getItemId() == item.getItemId());
	} else {
	  items.add(item);
	}
	return items.stream().anyMatch(i -> i.getCategoryId() == ECategory.ITEM_THAT_HAS_CATEGORY_PROMOTION.getId());
  }

  public double calculateTotalPricePromotionDiscount(double totalPrice) {
	if (totalPrice < 250) {
	  return 0;
	} else if (totalPrice < 5000) {
	  return 250;
	} else if (totalPrice < 10000) {
	  return 500;
	} else if (totalPrice < 50000) {
	  return 1000;
	} else {
	  return 2000;
	}
  }

  public boolean isCartHasDigitalItem(Cart cart) {
	return cart.getItems().stream().anyMatch(item -> item.getCategoryId() == ECategory.DIGITAL_ITEM.getId());
  }

  public ResponseEntity<BaseResponseDto> removeItemFromCart(int itemId) {
	BaseResponseDto response = new BaseResponseDto();
	Optional<Item> optionalItem = findItemById(itemId);
	if (optionalItem.isPresent()) {
	  Item item = optionalItem.get();
	  double cartTotalPriceAfterRemoveItem = cart.getTotalPrice() - item.getPrice() * item.getQuantity();
	  if (item instanceof DefaultItem defaultItem) {
		cartTotalPriceAfterRemoveItem = cart.getTotalPrice() - (defaultItem.getPrice() * defaultItem.getQuantity() + itemService.getVasItemTotalPriceSum(defaultItem));
	  }
	  response = applyDiscountToCart(cart,cartTotalPriceAfterRemoveItem,item,response);
	  if (response.isResult()) {
		cart.removeItemFromCart(item);
		cart.setTotalPrice(cartTotalPriceAfterRemoveItem);
	  }
	  return ResponseEntity.ok(response);
	} else {
	  response.setResult(false);
	  response.setMessage("Item not found in cart");
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
  }

  public ResponseEntity<BaseResponseDto> resetCart() {
	BaseResponseDto response = new BaseResponseDto();
	cart.resetCart();

	response.setResult(true);
	return ResponseEntity.ok(response);
  }

  public ResponseEntity<BaseResponseDto> displayCart() {
	BaseResponseDto response = new BaseResponseDto();
	response.setMessage(Mapper.toCartResponseDto(cart).toString());
	return ResponseEntity.ok(response);
  }

  public void setCartTotalPrice(double totalPrice) {
	cart.setTotalPrice(totalPrice);
  }
}
