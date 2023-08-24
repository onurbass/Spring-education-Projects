package com.socialmedia.rabbitmq.consumer;

import com.socialmedia.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivationConsumer {

    private final  UserProfileService userProfileService;

    @RabbitListener(queues = ("${rabbitmq.activation-queue}"))
    public void newUserCreate(Long authId){
        log.info("authId => {}",authId);
        userProfileService.acivateUser(authId);
    }


}
