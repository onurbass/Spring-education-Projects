package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.SkorModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkorProducer {


    private final RabbitTemplate rabbitTemplate;

    public  void  sendSkor(SkorModel model){
        rabbitTemplate.convertAndSend("tahmin-exchange","skor-key",model);
    }

}
