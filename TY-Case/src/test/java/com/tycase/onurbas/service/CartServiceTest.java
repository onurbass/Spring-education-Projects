package com.tycase.onurbas.service;

import com.tycase.onurbas.domain.cart.Cart;
import com.tycase.onurbas.domain.enums.ECategory;
import com.tycase.onurbas.domain.item.DefaultItem;
import com.tycase.onurbas.domain.item.DigitalItem;
import com.tycase.onurbas.domain.item.Item;
import com.tycase.onurbas.dto.request.ItemRequestDto;
import com.tycase.onurbas.dto.response.BaseResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tycase.onurbas.mapper.Mapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CartServiceTest {
  @Autowired
  CartService cartService;

  Cart cart;

  @Autowired
  ItemService itemService;

  @BeforeEach
  public void setUp() {
	cart = cartService.getCart();
  }

  @AfterEach
  public void tearDown() {
	cart.getItems().clear();
  }

  @Test
  void testAddItemToCart_ShouldReturnTrueWhenValidDefaultItem() {
	//given
	ItemRequestDto item = new ItemRequestDto();
	item.setCategoryId(ECategory.FURNITURE.getId());
	item.setQuantity(1);
	item.setItemId(1);

	//when
	ResponseEntity<BaseResponseDto> response = cartService.addItemToCart(item);
	//then
	Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
	Assertions.assertTrue(response.getBody().isResult());
	System.out.println(cart.getItems());
  }

  @Test
  void testAddItemToCart_ShouldReturnFalseWhenInvalidDefaultItem() {
	//given
	ItemRequestDto item = new ItemRequestDto();
	item.setCategoryId(ECategory.FURNITURE.getId());
	item.setQuantity(1);
	item.setItemId(1);
	item.setPrice(550000);
	//when
	ResponseEntity<BaseResponseDto> response = cartService.addItemToCart(item);
	//then
	Assertions.assertFalse(response.getStatusCode().is2xxSuccessful());
	Assertions.assertFalse(response.getBody().isResult());
  }

  @Test
  void testAddItemToCart_ShouldReturnTrueWhenValidDigitaltItem() {
	//given
	ItemRequestDto item = new ItemRequestDto();
	item.setCategoryId(ECategory.DIGITAL_ITEM.getId());
	item.setQuantity(1);
	item.setItemId(1);
	//when
	ResponseEntity<BaseResponseDto> response = cartService.addItemToCart(item);
	//then
	Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
	Assertions.assertTrue(response.getBody().isResult());
  }

  @Test
  void testAddItemToCart_ShouldReturnFalseWhileAddingDigitalItemWhenCartHasItemOtherThanDigitalItem() {
	//given
	ItemRequestDto defaultItem = new ItemRequestDto();
	defaultItem.setCategoryId(ECategory.FURNITURE.getId());
	defaultItem.setQuantity(1);
	defaultItem.setItemId(1);
	//when
	cartService.addItemToCart(defaultItem);
	//then
	Assertions.assertFalse(cartService.isCartHasDigitalItem(cart));

	//given
	ItemRequestDto digitalItem = new ItemRequestDto();
	digitalItem.setCategoryId(ECategory.DIGITAL_ITEM.getId());
	digitalItem.setQuantity(1);
	digitalItem.setItemId(2);
	//when
	ResponseEntity<BaseResponseDto> response = cartService.addItemToCart(digitalItem);
	//then
	Assertions.assertFalse(response.getStatusCode().is2xxSuccessful());
	Assertions.assertFalse(response.getBody().isResult());
  }

  @Test
  void testAddItemToCart_ShouldReturnFalseWhileAddingItemWhenCartHasDigitalItem() {
	//given
	ItemRequestDto digitalItem = new ItemRequestDto();
	digitalItem.setCategoryId(ECategory.DIGITAL_ITEM.getId());
	digitalItem.setQuantity(1);
	digitalItem.setItemId(2);
	cartService.addItemToCart(digitalItem);
	Assertions.assertTrue(cartService.isCartHasDigitalItem(cart));

	ItemRequestDto defaultItem = new ItemRequestDto();
	defaultItem.setCategoryId(ECategory.FURNITURE.getId());
	defaultItem.setQuantity(1);
	defaultItem.setItemId(1);

	//when
	ResponseEntity<BaseResponseDto> response = cartService.addItemToCart(defaultItem);

	//then
	Assertions.assertFalse(response.getStatusCode().is2xxSuccessful());
	Assertions.assertFalse(response.getBody().isResult());
  }

  @Test
  void testCheckItemAndCartIsValid_WhenItemLimitExceeded() {
	//given

	for (int i = 0; i < cart.getMAX_UNIQUE_ITEM_COUNT(); i++) {
	  cart.getItems().add(Item.builder().itemId(i).build());
	}
	Item item = new Item();
	item.setItemId(11);

	//when
	BaseResponseDto response = cartService.checkItemAndCartIsValid(cart,item);

	//then
	Assertions.assertFalse(response.isResult());
	Assertions.assertEquals("Unique item limit exceeded.",response.getMessage());
  }

  @Test
  void testCheckItemAndCartIsValid_WhenItemQuantityExceedsLimit() {
	//given

	Item item = new Item();
	item.setQuantity(11);

	//when
	BaseResponseDto response = cartService.checkItemAndCartIsValid(cart,item);

	//then
	Assertions.assertFalse(response.isResult());
	Assertions.assertEquals("Only " + item.getMAX_ITEM_QUANTITY() + " items can be added to the cart.",response.getMessage());
  }

  @Test
  void testCheckItemAndCartIsValid_WhenItemAlreadyExistsInCart() {
	//given

	Item item = new Item();
	cart.getItems().add(item);

	//when
	BaseResponseDto response = cartService.checkItemAndCartIsValid(cart,item);

	//then
	Assertions.assertFalse(response.isResult());
	Assertions.assertEquals("Item already exists in cart",response.getMessage());
  }

  @Test
  void testCheckItemAndCartIsValid_WhenItemQuantityIsZero() {
	//given
	Item item = new Item();
	item.setQuantity(0);

	//when
	BaseResponseDto response = cartService.checkItemAndCartIsValid(cart,item);

	//then
	Assertions.assertFalse(response.isResult());
	Assertions.assertEquals("Item quantity must be more than zero",response.getMessage());
  }

  @Test
  void testCheckItemAndCartIsValid_WhenCartQuantityExceedsLimitWithNewItem() {
	//given
	cart.getItems().add(Item.builder().itemId(1).quantity(29).build());
	Item item = new Item();
	item.setItemId(2);
	item.setQuantity(2);

	//when
	BaseResponseDto response = cartService.checkItemAndCartIsValid(cart,item);

	//then
	Assertions.assertFalse(response.isResult());
	Assertions.assertEquals("Cart cannot have more than " + cart.getMAX_TOTAL_ITEM_COUNT() + " items",response.getMessage());
  }

  @Test
  void testCheckItemAndCartIsValid_WhenVasItemTryToAdd() {
	//given
	BaseResponseDto response = cartService.checkItemAndCartIsValid(cart,Item.builder().categoryId(ECategory.VAS_ITEM.getId()).quantity(1).build());
	//then
	Assertions.assertFalse(response.isResult());
	Assertions.assertEquals("Vas Items cannot add to cart directly",response.getMessage());
  }

  @Test
  void testCheckItemAndCartIsValid_WhenAllConditionsAreMet() {
	//given
	Item item = new Item();
	item.setQuantity(1);
	//when
	BaseResponseDto response = cartService.checkItemAndCartIsValid(cart,item);

	//then
	Assertions.assertTrue(response.isResult());
  }

  @Test
  void testCheckItemAndCartIsValid_ShouldReturnTrueWhenMaxUniqueItemReached() {
	//given
	BaseResponseDto response = new BaseResponseDto();
	for (int i = 0; i < cart.getMAX_UNIQUE_ITEM_COUNT(); i++) {
	  cart.getItems().add(new Item());
	}
	//when
	boolean result = cartService.isMaxUniqueItemReached(cart);
	//then
	assertTrue(result);
  }

  @Test
  void testRemoveItemFromCart_ShouldReturnTrueWhenItemExistsInCart() {
	//given
	ItemRequestDto item1 = new ItemRequestDto();
	item1.setItemId(1);
	item1.setSellerId(1);
	item1.setPrice(100000);
	item1.setQuantity(1);
	cartService.addItemToCart(item1);

	ItemRequestDto item2 = new ItemRequestDto();
	item2.setItemId(2);
	item2.setSellerId(1);
	item2.setPrice(100000);
	item2.setQuantity(1);
	cartService.addItemToCart(item2);
	//when
	ResponseEntity<BaseResponseDto> responseEntity = cartService.removeItemFromCart(item1.getItemId());
	//then
	assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
	assertTrue(responseEntity.getBody().isResult());
  }

  @Test
  void testRemoveItemFromCart_ShouldReturnFalseWhenItemNotExistsInCart() {
	//when
	ResponseEntity<BaseResponseDto> responseEntity = cartService.removeItemFromCart(1);
	//then
	assertFalse(responseEntity.getStatusCode().is2xxSuccessful());
	assertFalse(responseEntity.getBody().isResult());
  }

  @Test
  void testIsCartFitForSameSellerPromotion_ShouldReturnFalseForSameSellerWhileAddingFirstItem() {
	//given
	ItemRequestDto item1 = new ItemRequestDto();
	item1.setSellerId(1);
	item1.setPrice(100000);
	Item item = Mapper.toItem(item1);
	//when
	boolean result = cartService.isCartFitForSameSellerPromotion(item,cart);
	//then
	assertFalse(result);
  }

  @Test
  void isCartFitForSameSellerPromotion_ShouldReturnTrueForSameSellerAddingSecondItem() {
	//given
	ItemRequestDto item1 = new ItemRequestDto();
	item1.setItemId(1);
	item1.setSellerId(1);
	item1.setPrice(100000);
	item1.setQuantity(1);
	cartService.addItemToCart(item1);

	ItemRequestDto item2 = new ItemRequestDto();
	item2.setSellerId(1);
	item2.setPrice(100000);
	item2.setItemId(2);
	Item item = Mapper.toItem(item2);
	//when
	boolean result = cartService.isCartFitForSameSellerPromotion(item,cart);
	//then
	assertTrue(result);
  }

  @Test
  void isCartFitForCategoryPromotion_ShouldReturnTrueForPrivilegedItem() {
	//given
	Item item = new DefaultItem();
	item.setCategoryId(ECategory.ITEM_THAT_HAS_CATEGORY_PROMOTION.getId());

	//when
	boolean result = cartService.isCartFitForCategoryPromotion(item,cart);

	//then
	assertTrue(result);
  }

  @Test
  void isCartFitForCategoryPromotion_ShouldReturnFalseForNonPrivilegedItem() {
	//given
	Item item = new DefaultItem();
	item.setCategoryId(ECategory.DIGITAL_ITEM.getId());

	//when
	boolean result = cartService.isCartFitForCategoryPromotion(item,cart);

	//then
	assertFalse(result);
  }

  @Test
  void verifyCartForDigitalItem_ShouldReturnFalseForNonDigitalItems() {
	//given
	List<Item> cartItems = new ArrayList<>();
	cartItems.add(new DefaultItem()); // DefaultItem, digital olmayan bir kategoriye sahip
	BaseResponseDto response = new BaseResponseDto();

	//when
	BaseResponseDto result = cartService.verifyCartForDigitalItem(cartItems,response);

	//then
	assertFalse(result.isResult());
	assertEquals("There are items in the cart other than digital items ",result.getMessage());
  }

  @Test
  void verifyCartForDigitalItem_ShouldReturnTrueForCorrectItem() {
	//given
	List<Item> cartItems = new ArrayList<>();
	cartItems.add(DigitalItem.builder().categoryId(ECategory.DIGITAL_ITEM.getId()).build()); // DefaultItem, digital olmayan bir kategoriye sahip
	BaseResponseDto response = new BaseResponseDto();
	//when
	BaseResponseDto result = cartService.verifyCartForDigitalItem(cartItems,response);

	//then
	assertTrue(result.isResult());
	assertEquals("Success!",result.getMessage());
  }

  @Test
  void verifyCartForDigitalItem_ShouldReturnFalseForMoreThan5DigitalItems() {
	//given
	List<Item> cartItems = new ArrayList<>();
	BaseResponseDto response = new BaseResponseDto();
	for (int i = 0; i < 6; i++) {
	  cartItems.add(DefaultItem.builder().categoryId(ECategory.DIGITAL_ITEM.getId()).build());
	}

	//when
	BaseResponseDto result = cartService.verifyCartForDigitalItem(cartItems,response);

	//then
	assertFalse(result.isResult());
	assertEquals("More than 5 digital items cannot be added to the cart",result.getMessage());
  }

  @Test
  void verifyCartForDigitalItem_ShouldReturnFalseForValidCart() {
	//given
	BaseResponseDto response = new BaseResponseDto();
	List<Item> cartItems = new ArrayList<>();
	for (int i = 0; i < 5; i++) {
	  cartService.addDigitalItemToCart(cart,new DigitalItem(),response);
	}
	//when
	BaseResponseDto result = cartService.verifyCartForDigitalItem(cartItems,response);
	//then
	assertFalse(result.isResult());
  }

  @Test
  void findDefaultItemById_ShouldReturnEmptyOptionalForNonDefaultItem() {
	//given
	int itemId = 2;
	Item item = new DigitalItem();
	item.setItemId(itemId);
	cart.addItemToCart(item);
	//when
	Optional<DefaultItem> result = cartService.findDefaultItemById(itemId,cart);

	//then
	assertTrue(result.isEmpty());
  }

  @Test
  void findDefaultItemById_ShouldReturnEmptyOptionalForNonexistentItem() {

	//when
	Optional<DefaultItem> result = cartService.findDefaultItemById(3,cart);

	//then
	assertTrue(result.isEmpty());
  }

  @Test
  void findDefaultItemById_ShouldReturnDefaultItemIfExists() {
	//given
	int itemId = 1;
	DefaultItem defaultItem = DefaultItem.builder().itemId(itemId).categoryId(ECategory.FURNITURE.getId()).quantity(1).build();
	cart.addItemToCart(defaultItem);

	//when
	Optional<DefaultItem> result = cartService.findDefaultItemById(itemId,cart);

	//then
	assertTrue(result.isPresent());
	assertEquals(itemId,result.get().getItemId());
  }

  @Test
  void resetCart_ShouldResetCart() {
	//given
	ItemRequestDto item = new ItemRequestDto();
	item.setCategoryId(ECategory.FURNITURE.getId());
	item.setQuantity(1);
	item.setItemId(1);
	cartService.addItemToCart(item);
	ItemRequestDto item2 = new ItemRequestDto();
	item2.setCategoryId(ECategory.FURNITURE.getId());
	item2.setQuantity(1);
	item2.setItemId(2);
	cartService.addItemToCart(item2);

	//when
	cartService.resetCart();

	//then
	assertEquals(0,cart.getItems().size());
  }

  @Test
  void calculateTotalPricePromotionDiscount() {

	double discount1 = cartService.calculateTotalPricePromotionDiscount(200.0);
	assertEquals(0,discount1);

	double discount2 = cartService.calculateTotalPricePromotionDiscount(3000.0);
	assertEquals(250,discount2);

	double discount3 = cartService.calculateTotalPricePromotionDiscount(7000.0);
	assertEquals(500,discount3);

	double discount4 = cartService.calculateTotalPricePromotionDiscount(25000.0);
	assertEquals(1000,discount4);

	double discount5 = cartService.calculateTotalPricePromotionDiscount(60000.0);
	assertEquals(2000,discount5);
  }
}