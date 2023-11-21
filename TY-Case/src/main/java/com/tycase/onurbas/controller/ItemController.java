package com.tycase.onurbas.controller;

import com.tycase.onurbas.dto.request.VasItemRequestDto;
import com.tycase.onurbas.dto.response.BaseResponseDto;
import com.tycase.onurbas.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
public class ItemController {
  private final ItemService itemService;

  @PostMapping("/{id}")
  public ResponseEntity<BaseResponseDto> addVasItemToDefaultItem(@PathVariable int id,@RequestBody @Valid VasItemRequestDto vasItemRequestDto) {
	return itemService.addVasItemToItem(id,vasItemRequestDto);
  }
}
