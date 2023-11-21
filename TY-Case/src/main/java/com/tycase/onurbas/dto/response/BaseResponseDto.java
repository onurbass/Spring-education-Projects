package com.tycase.onurbas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponseDto {
  @Builder.Default
  private String message = "Success!";
  @Builder.Default
  private boolean result=true;
}
