package com.onurbas.exception;

import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorDetails {

   private Date timestamp;
   private String message;
   private String details;



}
