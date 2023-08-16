package com.onurbas.limitsservice.bean;

import com.onurbas.limitsservice.configuration.Configuration;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Limits {


  private int min;
  private int max;
}
