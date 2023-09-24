package com.onurbas.rabbitmq.consumer;

import com.onurbas.dto.response.UserProfileResponseDto;
import com.onurbas.exception.ErrorType;
import com.onurbas.exception.UserManagerException;
import com.onurbas.rabbitmq.model.CreatePostModel;
import com.onurbas.repository.entity.UserProfile;
import com.onurbas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreatePostConsumer {
    //http:10.20.123.23/auth/all-users
    private final UserService userService;

    @RabbitListener(queues = "post-queue")
    public Object createPost(CreatePostModel createPostModel){ //authId
       UserProfile userProfile = userService.findByUserWithAuthId(createPostModel.getAuthId()).orElseThrow(()->new UserManagerException(ErrorType.USER_NOT_FOUND));

        return UserProfileResponseDto.builder()
                                     .userId(userProfile.getId())
                                     .userAvatar(userProfile.getAvatar())
                                     .username(userProfile.getUsername())
                                     .build();
    }
}
