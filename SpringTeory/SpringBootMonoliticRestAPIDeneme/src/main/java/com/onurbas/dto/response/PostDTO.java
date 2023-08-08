package com.onurbas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
  private Long id;
  private String title;
  private String content;
  private LocalDateTime date;
  private Long userId;
  private Long categoryId;
}