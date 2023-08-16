package com.onurbas.rabbitmq.consumer;

import com.onurbas.rabbitmq.model.RegisterModel;
import com.onurbas.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterConsumer {

    private final  UserProfileService userProfileService;

    @RabbitListener(queues = ("registerQueue"))
    public void newUserCreate(RegisterModel registerModel){
        log.info("User {}",registerModel.toString());

        userProfileService.createNewUserWithRabbitmq(registerModel);
    }


}
