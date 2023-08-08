package com.onurbas.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
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