package com.socialmedia.rabbitmq.producer;

import com.socialmedia.rabbitmq.model.RegisterElasticModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterElasticProducer {
  private final RabbitTemplate rabbitTemplate;

  @Value("${rabbitmq.user-exchange}")
  private String exchange;

  @Value("${rabbitmq.register-elastic-binding-key}")
  private String elasticBindingKey;

  public void sendNewUser(RegisterElasticModel model) {
	rabbitTemplate.convertAndSend(exchange, elasticBindingKey, model);
  }
}
