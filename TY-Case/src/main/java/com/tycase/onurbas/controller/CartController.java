package com.tycase.onurbas.controller;

import com.tycase.onurbas.dto.request.ItemRequestDto;
import com.tycase.onurbas.dto.response.BaseResponseDto;
import com.tycase.onurbas.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
  private final CartService cartService;

  @PostMapping
  public ResponseEntity<BaseResponseDto> addItemToCart(@RequestBody @Valid ItemRequestDto itemRequestDto) {

	return cartService.addItemToCart(itemRequestDto);
  }

  @GetMapping
  public ResponseEntity<BaseResponseDto> displayCart() {
	return cartService.displayCart();
  }

  @DeleteMapping("/{itemId}")
  public ResponseEntity<BaseResponseDto> removeItemFromCart(@PathVariable int itemId) {
	return cartService.removeItemFromCart(itemId);
  }

  @DeleteMapping
  public ResponseEntity<BaseResponseDto> resetCart() {
	return cartService.resetCart();
  }
}
