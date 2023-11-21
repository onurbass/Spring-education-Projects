package com.tycase.onurbas.service;

import com.tycase.onurbas.domain.cart.Cart;
import com.tycase.onurbas.domain.item.DefaultItem;
import com.tycase.onurbas.domain.item.VasItem;
import com.tycase.onurbas.dto.request.ItemRequestDto;
import com.tycase.onurbas.dto.request.VasItemRequestDto;
import com.tycase.onurbas.dto.response.BaseResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ItemServiceTest {
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
  void testAddVasItemToItem_ShouldReturnTrueWhileAddingValidVasItemToValidDefaultItem() {
	//given
	ItemRequestDto itemRequestDto = new ItemRequestDto();
	itemRequestDto.setItemId(1);
	itemRequestDto.setQuantity(1);
	itemRequestDto.setPrice(50000);
	itemRequestDto.setCategoryId(1001);
	itemRequestDto.setSellerId(1);
	cartService.addItemToCart(itemRequestDto);

	VasItemRequestDto vasItemRequestDto = new VasItemRequestDto();
	vasItemRequestDto.setVasItemId(1);
	vasItemRequestDto.setQuantity(1);
	vasItemRequestDto.setPrice(10000);
	vasItemRequestDto.setCategoryId(3242);
	vasItemRequestDto.setSellerId(5003);
	//when
	ResponseEntity<BaseResponseDto> responseEntity = itemService.addVasItemToItem(1,vasItemRequestDto);
	//then
	Assertions.assertTrue(responseEntity.getBody().isResult());
  }

  @Test
  void testCheckVasItemAndDefaultItemIsValid_ShouldReturnFalseWhenVasItemAlreadyExistInItem() {
	//given
	DefaultItem defaultItem = new DefaultItem();
	VasItem vasItem = new VasItem();
	VasItem existingVasItem = new VasItem();
	defaultItem.getVasItems().add(existingVasItem);

	//when
	BaseResponseDto response = itemService.checkVasItemAndDefaultItemIsValid(defaultItem,vasItem,new BaseResponseDto());

	//then
	Assertions.assertFalse(response.isResult());
	Assertions.assertEquals("Vas Item already exists in Item",response.getMessage());
  }

  @Test
  void testCheckVasItemAndDefaultItemIsValid_ShouldReturnFalseWhenVasItemQuantityIsZero() {
	//given
	DefaultItem defaultItem = new DefaultItem();
	VasItem vasItem = new VasItem();
	vasItem.setQuantity(0);

	//when
	BaseResponseDto response = itemService.checkVasItemAndDefaultItemIsValid(defaultItem,vasItem,new BaseResponseDto());

	//then
	Assertions.assertFalse(response.isResult());
	Assertions.assertEquals("Invalid vas item quantity",response.getMessage());
  }

  @Test
  void testCheckVasItemAndDefaultItemIsValid_ShouldReturnFalseWhenVasItemQuantityExceedsMaxVasItemThreshold() {
	//given
	DefaultItem defaultItem = new DefaultItem();
	defaultItem.setPrice(100000);
	defaultItem.setQuantity(1);

	for (int i = 0; i < defaultItem.getMAX_VAS_ITEM_THRESHOLD(); i++) {
	  defaultItem.getVasItems().add(VasItem.builder().categoryId(3242).sellerId(5003).itemId(i).quantity(1).build());
	}
	VasItem vasItem = new VasItem();
	vasItem.setQuantity(1);
	vasItem.setItemId(7);
	vasItem.setCategoryId(3242);
	vasItem.setSellerId(5003);

	//when
	BaseResponseDto response = itemService.checkVasItemAndDefaultItemIsValid(defaultItem,vasItem,new BaseResponseDto());

	//then
	Assertions.assertFalse(response.isResult());
	Assertions.assertEquals("A default item cannot have more than " + defaultItem.getMAX_VAS_ITEM_THRESHOLD() + " vas items",response.getMessage());
  }

  @Test
  void testCheckVasItemAndDefaultItemIsValid_ShouldReturnFalseWhenVasItemIsMoreExpensiveThanDefaultItem() {
	//given
	DefaultItem defaultItem = new DefaultItem();
	defaultItem.setPrice(100000);
	defaultItem.setQuantity(1);
	VasItem vasItem = new VasItem();
	vasItem.setPrice(120000);
	vasItem.setQuantity(1);

	//when
	BaseResponseDto response = itemService.checkVasItemAndDefaultItemIsValid(defaultItem,vasItem,new BaseResponseDto());

	//then
	Assertions.assertFalse(response.isResult());
	Assertions.assertEquals("Vas item cannot be more expensive than the default item.",response.getMessage());
  }

  @Test
  void testCheckVasItemAndDefaultItemIsValid_ShouldReturnFalseWhenDefaultItemIsNotFurnitureOrElectronicItem() {
	//given
	DefaultItem defaultItem = new DefaultItem();
	defaultItem.setCategoryId(1000); // This is not a furniture or electronic item category.
	defaultItem.setPrice(100000);
	defaultItem.setQuantity(1);
	VasItem vasItem = new VasItem();
	vasItem.setQuantity(1);
	vasItem.setPrice(10000);

	//when
	BaseResponseDto response = itemService.checkVasItemAndDefaultItemIsValid(defaultItem,vasItem,new BaseResponseDto());

	//then
	Assertions.assertFalse(response.isResult());
	Assertions.assertEquals("Only furniture or electronic items can have VAS item",response.getMessage());
  }

  @Test
  void testCheckVasItemAndDefaultItemIsValid_ShouldReturnFalseWhenVasItemIsNotValidVasItem() {
	//given
	DefaultItem defaultItem = new DefaultItem();
	defaultItem.setCategoryId(1001);

	defaultItem.setPrice(100000);
	VasItem vasItem = new VasItem();
	vasItem.setCategoryId(3200);
	vasItem.setQuantity(1);
	vasItem.setPrice(10000);

	//when
	BaseResponseDto response = itemService.checkVasItemAndDefaultItemIsValid(defaultItem,vasItem,new BaseResponseDto());

	//then
	Assertions.assertFalse(response.isResult());
	Assertions.assertEquals("Invalid VAS item",response.getMessage());
  }
}