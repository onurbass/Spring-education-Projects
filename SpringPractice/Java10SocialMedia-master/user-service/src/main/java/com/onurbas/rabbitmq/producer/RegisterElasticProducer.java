package com.onurbas.rabbitmq.producer;

import com.onurbas.rabbitmq.model.RegisterElasticModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterElasticProducer {

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.register-elastic-binding}")
    private String registerElasticBinding;
    @Value("${rabbitmq.user-exchange}")
    private String exchange;

    public void sendNewUser(RegisterElasticModel model){
        rabbitTemplate.convertAndSend(exchange,registerElasticBinding,model);
    }
}
