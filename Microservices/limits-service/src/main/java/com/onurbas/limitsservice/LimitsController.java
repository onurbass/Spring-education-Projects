package com.onurbas.limitsservice;

import com.onurbas.limitsservice.bean.Limits;
import com.onurbas.limitsservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

  private final Configuration configuration;

  @Autowired
  public LimitsController(Configuration configuration) {
	this.configuration = configuration;
  }

  @GetMapping("/limits")
  public Limits retrieveLimits() {
	return new Limits(configuration.getMin(),configuration.getMax());
//return new Limits(1,1000);
  }
}
