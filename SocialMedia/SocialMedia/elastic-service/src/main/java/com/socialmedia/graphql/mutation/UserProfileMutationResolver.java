package com.socialmedia.graphql.mutation;

import com.socialmedia.excepiton.ElasticManagerException;
import com.socialmedia.excepiton.ErrorType;
import com.socialmedia.graphql.model.UserProfileInput;
import com.socialmedia.mapper.IUserMapper;
import com.socialmedia.service.UserProfileService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver  implements GraphQLMutationResolver {

    private final UserProfileService userProfileService;


    public Boolean createUserProfile(UserProfileInput userProfileInput){
        try {
            userProfileService.save(IUserMapper.INSTANCE.toUserProfile(userProfileInput));
            return  true;
        }catch (Exception e){
         throw    new ElasticManagerException(ErrorType.USER_NOT_CREATED);
        }

    }
}
