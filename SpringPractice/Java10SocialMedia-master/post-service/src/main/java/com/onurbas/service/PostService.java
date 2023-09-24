package com.onurbas.service;

import com.onurbas.dto.request.CreateNewPostRequestDto;
import com.onurbas.dto.response.UserProfileResponseDto;
import com.onurbas.exception.ErrorType;
import com.onurbas.exception.PostManagerException;
import com.onurbas.rabbitmq.model.CreatePostModel;
import com.onurbas.rabbitmq.producer.CreatePostProducer;
import com.onurbas.repository.IPostRepository;
import com.onurbas.repository.entity.Post;
import com.onurbas.utility.JwtTokenManager;
import com.onurbas.utility.ServiceManager;
import org.springframework.stereotype.Service;



@Service
public class PostService extends ServiceManager<Post, String> {
    private final IPostRepository postRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CreatePostProducer createPostProducer;


    public PostService(IPostRepository postRepository, JwtTokenManager jwtTokenManager, CreatePostProducer createPostProducer) {
        super(postRepository);
        this.postRepository = postRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.createPostProducer = createPostProducer;
    }

/*
bu metodun controller' ını yazınız.
createPost işlemi için user-service' de consumer işlemlerini yapınız.
 */
    public Post createPost(String token, CreateNewPostRequestDto dto){
        Long authId = jwtTokenManager.getAuthIdFromToken(token)
                .orElseThrow(() -> {throw new PostManagerException(ErrorType.INVALID_TOKEN);});
        CreatePostModel createPostModel = CreatePostModel.builder()
                .authId(authId)
                .build();
        UserProfileResponseDto userProfile = (UserProfileResponseDto) createPostProducer.createPost(createPostModel);
        Post post = Post.builder()
                .userId(userProfile.getUserId())
                .username(userProfile.getUsername())
                .userAvatar(userProfile.getUserAvatar())
                .content(dto.getContent())
                .mediaUrls(dto.getMediaUrls())
                .build();
        return save(post);
    }
}
