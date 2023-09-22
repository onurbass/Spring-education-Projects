package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.CreatePostModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostProducer {

    private final RabbitTemplate rabbitTemplate;
    private String exchange = "post-exchange";
    private String createPostBindingKey = "post-bindingkey";

    public Object createPost(CreatePostModel model){
        return rabbitTemplate.convertSendAndReceive(exchange, createPostBindingKey, model);
    }
}
