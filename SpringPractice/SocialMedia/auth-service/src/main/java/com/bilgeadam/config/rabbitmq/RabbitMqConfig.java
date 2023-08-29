package com.bilgeadam.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
  private String exchange = "auth-exchange";
  private String registerBindingKey = "register-bindingKey";
  private String registerQueueName = "register-queue";

  @Bean
  DirectExchange exchange() {
	return new DirectExchange(exchange);
  }

  @Bean
  Queue registerQueue() {
	return new Queue(registerQueueName);
  }

  @Bean
  public Binding bindingRegister(final Queue registerQueue,final DirectExchange exchange) {
	return new Binding(registerQueueName,Binding.DestinationType.QUEUE,exchange.getName(),registerBindingKey,null);
  }
}
