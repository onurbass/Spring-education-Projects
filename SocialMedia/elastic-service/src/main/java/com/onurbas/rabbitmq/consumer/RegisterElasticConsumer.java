package com.onurbas.rabbitmq.consumer;

import com.onurbas.mapper.IUserMapper;
import com.onurbas.rabbitmq.model.RegisterElasticModel;
import com.onurbas.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterElasticConsumer {

    private final UserProfileService userProfileService;

    @RabbitListener(queues = "${rabbitmq.register-elastic-queue}")
    public  void newUserCreate(RegisterElasticModel model){
        log.info("User {}",model);
        userProfileService.save(IUserMapper.INSTANCE.toUserProfile(model));
    }

}
